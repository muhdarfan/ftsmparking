import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI extends JFrame implements ActionListener {
    private JPanel panel1, panel2;
    private JLabel welcomeLbl;
    private JButton addPBtn, addVBtn, listBtn, tesBtn;
    private Container content;

    public TestGUI() {
        content = getContentPane();
        content.setLayout(new GridLayout(2, 1));

        // Button
        addPBtn = new JButton("Add Person");
        addVBtn = new JButton("Add Vehicles");
        listBtn = new JButton("List Person");
        tesBtn = new JButton("List Person");
        addPBtn.addActionListener(this);

        // Panel
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,3));
        panel1.add(addPBtn);
        panel1.add(addVBtn);
        panel1.add(listBtn);
        panel1.add(tesBtn);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        welcomeLbl = new JLabel("WELCOME TO FTSM PARKING LOT MANAGEMENT SYSTEM!");
        panel2.add(welcomeLbl, JLabel.CENTER);

        content.add(panel1, 0);
        content.add(panel2, 1);
    }

    public void actionPerformed(ActionEvent evt) {
        Object obj = evt.getSource();

        try {
            if (obj == addPBtn) {

            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ReplacePanel(JPanel _panel) {
        content.remove(1);
        content.add(_panel, 1);
    }

    public static void main(String[] args) {
        TestGUI frame = new TestGUI();
        frame.setTitle("FTSM Parking Lot");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
