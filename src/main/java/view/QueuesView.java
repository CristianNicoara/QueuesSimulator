package view;

import javax.swing.*;
import java.awt.*;

public class QueuesView extends JFrame {
    private JTextArea simArea;

    public QueuesView() {
        this.setBounds(100, 100, 820, 593);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel simLbl = new JLabel("SIMULATION STATS");
        simLbl.setHorizontalAlignment(SwingConstants.CENTER);
        simLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        simLbl.setBounds(254, 35, 295, 38);
        this.getContentPane().add(simLbl);

        simArea = new JTextArea();
        simArea.setEnabled(false);
        simArea.setTabSize(4);
        simArea.setLineWrap(true);
        simArea.setBounds(51, 84, 721, 443);
        this.getContentPane().add(simArea);
    }

    public void setSimArea(String info) {
        this.simArea.setText(info);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void refresh() {
        simArea.setText(null);
    }
}
