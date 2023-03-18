package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputView extends JFrame {
    private JTextField nrClientsField;
    private JTextField nrQueuesField;
    private JTextField simIntervalField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JTextField minServiceTimeField;
    private JTextField maxServiceTimeField;

    private JButton queuesEvolutionBtn;

    public String getNrClientsField() {
        return nrClientsField.getText();
    }

    public String getNrQueuesField() {
        return nrQueuesField.getText();
    }

    public String getSimIntervalField() {
        return simIntervalField.getText();
    }

    public String getMinArrivalTimeField() {
        return minArrivalTimeField.getText();
    }

    public String getMaxArrivalTimeField() {
        return maxArrivalTimeField.getText();
    }

    public String getMinServiceTimeField() {
        return minServiceTimeField.getText();
    }

    public String getMaxServiceTimeField() {
        return maxServiceTimeField.getText();
    }

    public InputView() {
        this.setBounds(100, 100, 816, 528);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("INPUT DATA");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(241, 11, 317, 52);
        this.getContentPane().add(lblNewLabel);

        JLabel lblNrClients = new JLabel("Number Of Clients");
        lblNrClients.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrClients.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrClients.setBounds(50, 87, 170, 24);
        this.getContentPane().add(lblNrClients);

        JLabel lblNrQueues = new JLabel("Number Of Queues");
        lblNrQueues.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrQueues.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrQueues.setBounds(50, 132, 170, 24);
        this.getContentPane().add(lblNrQueues);

        JLabel lblSimInterval = new JLabel("Simulation Interval");
        lblSimInterval.setHorizontalAlignment(SwingConstants.CENTER);
        lblSimInterval.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSimInterval.setBounds(50, 176, 170, 24);
        this.getContentPane().add(lblSimInterval);

        JLabel lblMinArrivalTime = new JLabel("Minimum Arrival Time");
        lblMinArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblMinArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMinArrivalTime.setBounds(50, 222, 170, 24);
        this.getContentPane().add(lblMinArrivalTime);

        JLabel lblMaxArrivalTime = new JLabel("Maximum Arrival Time");
        lblMaxArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaxArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaxArrivalTime.setBounds(50, 267, 170, 24);
        this.getContentPane().add(lblMaxArrivalTime);

        JLabel lblMinServiceTime = new JLabel("Minimum Service Time");
        lblMinServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblMinServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMinServiceTime.setBounds(50, 310, 170, 24);
        this.getContentPane().add(lblMinServiceTime);

        JLabel lblMaxServiceTime = new JLabel("Maximum Service Time");
        lblMaxServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaxServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaxServiceTime.setBounds(50, 353, 170, 24);
        this.getContentPane().add(lblMaxServiceTime);

        nrClientsField = new JTextField();
        nrClientsField.setBounds(241, 89, 432, 24);
        this.getContentPane().add(nrClientsField);
        nrClientsField.setColumns(10);

        nrQueuesField = new JTextField();
        nrQueuesField.setColumns(10);
        nrQueuesField.setBounds(241, 132, 432, 24);
        this.getContentPane().add(nrQueuesField);

        simIntervalField = new JTextField();
        simIntervalField.setColumns(10);
        simIntervalField.setBounds(241, 171, 432, 24);
        this.getContentPane().add(simIntervalField);

        minArrivalTimeField = new JTextField();
        minArrivalTimeField.setColumns(10);
        minArrivalTimeField.setBounds(241, 222, 432, 24);
        this.getContentPane().add(minArrivalTimeField);

        maxArrivalTimeField = new JTextField();
        maxArrivalTimeField.setColumns(10);
        maxArrivalTimeField.setBounds(241, 267, 432, 24);
        this.getContentPane().add(maxArrivalTimeField);

        minServiceTimeField = new JTextField();
        minServiceTimeField.setColumns(10);
        minServiceTimeField.setBounds(241, 310, 432, 24);
        this.getContentPane().add(minServiceTimeField);

        maxServiceTimeField = new JTextField();
        maxServiceTimeField.setColumns(10);
        maxServiceTimeField.setBounds(241, 353, 432, 24);
        this.getContentPane().add(maxServiceTimeField);

        queuesEvolutionBtn = new JButton("View Queues Evolution");
        queuesEvolutionBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        queuesEvolutionBtn.setBounds(299, 408, 202, 35);
        this.getContentPane().add(queuesEvolutionBtn);

        setVisible(true);
    }

    public int getNrClients() {
        return Integer.parseInt(nrClientsField.getText());
    }

    public int getNrQueues() {
        return Integer.parseInt(nrQueuesField.getText());
    }

    public int getSimIntegrval() {
        return Integer.parseInt(simIntervalField.getText());
    }

    public int getMinArrivalTime() {
        return Integer.parseInt(minArrivalTimeField.getText());
    }

    public int getMaxArrivalTime() {
        return Integer.parseInt(maxArrivalTimeField.getText());
    }

    public int getMinServiceTime() {
        return Integer.parseInt(minServiceTimeField.getText());
    }

    public int getMaxServiceTime() {
        return Integer.parseInt(maxServiceTimeField.getText());
    }

    public void addButtonListener(ActionListener action) {
        queuesEvolutionBtn.addActionListener(action);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
