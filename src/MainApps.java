import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainApps extends JFrame implements ActionListener {

    private ArrayList<Person> _personList;
    // Component List
    private JLabel labelWelcome;
    private JButton personMenuBtn, vehicleMenuBtn, parkingMenuBtn;
    private JPanel panel1, panel2, panel3;

    public MainApps() {
        // Initialize
        _personList = new ArrayList<Person>();
        Container content = getContentPane();

        content.setLayout(new GridBagLayout());
        // Panel
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(Color.BLUE);
        panel2 = new JPanel(new FlowLayout());
        panel2.setBackground(Color.RED);
        // Label
        labelWelcome = new JLabel("Welcome to FTSM Parking Lots Management System");

        // Button

        personMenuBtn = new JButton("Person");
        personMenuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        vehicleMenuBtn = new JButton("Vehicle");
        vehicleMenuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        parkingMenuBtn = new JButton("Parking");
        parkingMenuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel1.add(personMenuBtn);
        panel1.add(vehicleMenuBtn);
        panel1.add(parkingMenuBtn);

        panel2.add(labelWelcome);

        content.add(panel2);
        content.add(panel1);
    }

    @Override
    public void actionPerformed(ActionEvent obj) {

    }

    public static void main(String[] args) {
        // Create Person
        // Create Vehicle
        // Create Parking Lots
        // Read Information

        MainApps frame = new MainApps();
        frame.setTitle("FTSM - Parking Lots Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /*
        String response = JOptionPane.showInputDialog(null,
 "What is your name?",
 "Enter your name",
 JOptionPane.QUESTION_MESSAGE);
         */
    }
}
