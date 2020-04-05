import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

import javax.swing.*;

public class Owner extends JFrame implements ActionListener {
    Scanner input = new Scanner(System.in);

    JFrame frame1 = new JFrame(" Welcome to UKM Parking Lot");
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel(new GridLayout(3, 3, 5, 10));
    JLabel label1 = new JLabel("Name: ", SwingConstants.CENTER);
    JLabel label2 = new JLabel("ID: ", SwingConstants.CENTER);
    JLabel label3 = new JLabel("Vehicle Registry: ", SwingConstants.CENTER);
    JButton button1 = new JButton(" Add ");
    JButton button2 = new JButton(" Login ");
    JTextArea txtArea1 = new JTextArea(1, 20);
    JTextArea txtArea2 = new JTextArea(1, 20);
    JTextArea txtArea3 = new JTextArea(1, 20);

    public Owner() {
        OwnerInfo();
    }

    public void OwnerInfo() {

        frame1.setVisible(true);
        frame1.setSize(400, 300);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.add(panel1, BorderLayout.SOUTH);
        frame1.add(panel2, BorderLayout.NORTH);
        panel1.setSize(300, 200);
        panel1.add(button1);
        panel1.add(button2);
        panel1.setVisible(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
        panel2.setSize(300, 200);
        panel2.add(label1);
        panel2.add(txtArea1);
        panel2.add(label2);
        panel2.add(txtArea2);
        panel2.add(label3);
        panel2.add(txtArea3);
        panel2.setVisible(true);
        txtArea1.setVisible(true);
        txtArea1.setBounds(50, 20, 100, 100);
        txtArea1.setEditable(true);
        txtArea2.setVisible(true);
        txtArea2.setEditable(true);
        txtArea2.setBounds(100, 40, 100, 100);
        label1.setVisible(true);
        label2.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Owner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == button1) {

            try {
                Student inSTU = new Student(txtArea1.getText(), txtArea2.getText());
                //Vehicles inVEH = new Vehicles(txtArea3.getText());

                File file = new File("file.txt");

                FileWriter fwriter = new FileWriter(file, true);
                fwriter.write(inSTU.toString() + '\n');
                //fwriter.write(inVEH.toString());
                fwriter.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}