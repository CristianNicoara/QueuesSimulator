package model;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private AtomicInteger totalWaitindTime;
    private AtomicInteger totalServiceTime;
    private int queueNumber;

    public Queue(int queueNumber) {
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.totalWaitindTime = new AtomicInteger(0);
        this.totalServiceTime = new AtomicInteger(0);
        this.queueNumber = queueNumber;
    }

    public void addClient(Client client) throws InterruptedException {
        this.clients.put(client);
        waitingPeriod.addAndGet(client.getServiceTime());
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public AtomicInteger getTotalWaitindTime() {
        return totalWaitindTime;
    }

    public void setTotalWaitindTime(int time) {
        this.totalWaitindTime.addAndGet(time);
    }

    public AtomicInteger getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(int serviceTime) {
        this.totalServiceTime.addAndGet(serviceTime);
    }

    @Override
    public void run() {
        while (true) {
            Client c = new Client();
            try {
                c = clients.element();
            } catch (NoSuchElementException exception) {

            }
            int n = c.getServiceTime();
            for (int i = 0; i < n; i++) {
                ;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingPeriod.decrementAndGet();
                c.setServiceTime(c.getServiceTime() - 1);
            }
        }
    }

    public String toStringLogger() {
        String clientsId = "";
        for (int i = 0; i < clients.size(); i++) {
            Client c = new Client();
            try {
                c = clients.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clientsId += "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ");";
            clients.add(c);
        }
        return "Queue " + queueNumber + " : " + clientsId + "\n";
    }

    @Override
    public String toString() {
        String clientsId = "";
        for (int i = 0; i < clients.size(); i++) {
            Client c = new Client();
            try {
                c = clients.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clientsId += c.getId() + " ";
            clients.add(c);
        }
        return "Queue " + queueNumber + " : " + clientsId + "\n";
    }
}
