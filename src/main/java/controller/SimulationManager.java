package controller;

import model.Client;
import view.InputView;
import view.QueuesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SimulationManager implements Runnable {

    private InputView inputView;
    private QueuesView queuesView;
    private int simInterval;
    private int nrClients;
    private int nrQueues;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private Scheduler scheduler;
    private List<Client> clients;
    int numberClients;

    private static Logger logger = LogManager.getLogger(SimulationManager.class);

    public SimulationManager(InputView inputView, QueuesView queuesView) {
        this.inputView = inputView;
        this.queuesView = queuesView;
        this.simInterval = inputView.getSimIntegrval();
        this.nrClients = inputView.getNrClients();
        this.numberClients = inputView.getNrClients();
        this.nrQueues = inputView.getNrQueues();
        this.minArrivalTime = inputView.getMinArrivalTime();
        this.maxArrivalTime = inputView.getMaxArrivalTime();
        this.minServiceTime = inputView.getMinServiceTime();
        this.maxServiceTime = inputView.getMaxServiceTime();
        this.scheduler = new Scheduler(nrQueues);
        generateRandomClients();
    }


    public void generateRandomClients() {
        clients = new ArrayList<>();
        int rangeServiceTime = (maxServiceTime - minServiceTime) + 1;
        int rangeArrivalTime = (maxArrivalTime - minArrivalTime) + 1;
        for (int i = 0; i < nrClients; i++) {
            int serviceTime = (int) (Math.random() * rangeServiceTime) + minServiceTime;
            int arrivalTime = (int) (Math.random() * rangeArrivalTime) + minArrivalTime;
            clients.add(new Client(i + 1, serviceTime, arrivalTime));
        }
        Collections.sort(clients);
    }

    public boolean endSimulation(int nrClients) {
        boolean endSim = false;
        if (nrClients == 0) {
            for (int i = 0; i < nrQueues; i++) {
                if (scheduler.getQueues().get(i).getClients().isEmpty()) {
                    endSim = true;
                } else {
                    endSim = false;
                    break;
                }
            }
        }
        return endSim;
    }

    public void showQueues(int clock) {
        String afisare;
        afisare = "Time: " + clock + "\n";
        for (int i = 0; i < nrQueues; i++) {
            afisare += scheduler.getQueues().get(i).toString();
        }
        queuesView.setSimArea(afisare);
    }

    public void writeLog(int clock) {
        String afisare = "Time: " + clock + "\n" + "Waiting Clients: ";
        for (int i = 0; i < nrClients; i++) {
            afisare += clients.get(i).toString();
        }
        afisare += "\n";
        for (int i = 0; i < nrQueues; i++) {
            afisare += scheduler.getQueues().get(i).toStringLogger();
        }
        logger.info(afisare);
    }

    public double avgWaitingTime() {
        double avg;
        int sum = 0;
        for (int i = 0; i < nrQueues; i++) {
            sum += scheduler.getQueues().get(i).getTotalWaitindTime().get();
        }
        avg = (double) sum / numberClients;

        return avg;
    }

    public int totalNrClients() {
        int nrClienti = 0;
        for (int i = 0; i < nrQueues; i++) {
            nrClienti += scheduler.getQueues().get(i).getClients().size();
        }
        return nrClienti;
    }

    public double avgServiceTime() {
        double avg;
        int sum = 0;
        for (int i = 0; i < nrQueues; i++) {
            sum += scheduler.getQueues().get(i).getTotalServiceTime().get();
        }
        avg = (double) sum / numberClients;

        return avg;
    }

    int peakTime;
    int maxNrClienti = 0;

    @Override
    public void run() {
        int clock = 0;
        for (int i = 0; i < clients.size(); i++) {
            System.out.println("Id = " + clients.get(i).getId() + " Service Time = " + clients.get(i).getServiceTime() + " Arrival Time = " + clients.get(i).getArrivalTime());
        }
        while (clock <= simInterval || !endSimulation(nrClients)) {
            if (endSimulation(nrClients))
                break;
            for (int j = 0; j < nrClients; j++) {
                if (clients.get(j).getArrivalTime() == clock) {
                    try {
                        scheduler.dispatchClient(clients.get(j));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clients.remove(clients.get(j));
                    nrClients--;
                    j--;
                }
            }
            try {
                for (int i = 0; i < nrQueues; i++) {
                    if (!scheduler.getQueues().get(i).getClients().isEmpty()) {
                        if (scheduler.getQueues().get(i).getClients().element().getServiceTime() == 0) {
                            scheduler.getQueues().get(i).getClients().remove();
                        }
                    }
                }
            } catch (NoSuchElementException exception) {

            }
            int c = totalNrClients();
            if (c > maxNrClienti) {
                maxNrClienti = c;
                peakTime = clock;
            }
            showQueues(clock);
            writeLog(clock);
            clock++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queuesView.refresh();
        }
        double averageWaitingTime = avgWaitingTime();
        double averageServiceTime = avgServiceTime();
        queuesView.refresh();
        queuesView.showMessage("Simulation Finished!\nAverage waiting time = " + averageWaitingTime + "\nAverage service time = " + averageServiceTime + "\nPeak hour = " + peakTime);
    }
}
