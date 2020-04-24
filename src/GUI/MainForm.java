package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements ActionListener {
    private final Container content;
    private JPanel rootPanel;

    public MainForm() {
        setTitle("FTSM Parking Lot Management System");
        setSize(600, 400);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = getContentPane();
        content.add(rootPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}
