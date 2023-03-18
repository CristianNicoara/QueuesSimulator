package controller;

import view.InputView;
import view.QueuesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private InputView inputView;
    private QueuesView queuesView;

    public MainController(InputView inputView, QueuesView queuesView) {
        this.inputView = inputView;
        this.queuesView = queuesView;

        this.inputView.addButtonListener(new Simulation());
    }

    class Simulation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (inputView.getNrClientsField().equals("") || inputView.getNrQueuesField().equals("") || inputView.getSimIntervalField().equals("") || inputView.getMinArrivalTimeField().equals("") || inputView.getMaxArrivalTimeField().equals("") || inputView.getMinServiceTimeField().equals("") || inputView.getMaxServiceTimeField().equals("")){
                inputView.showMessage("Introduceti toate datele de intrare");
            }else {
                inputView.setVisible(false);
                queuesView.setVisible(true);
                SimulationManager simulationManager = new SimulationManager(inputView, queuesView);
                Thread mainThread = new Thread(simulationManager);
                mainThread.start();
            }
        }
    }
}
