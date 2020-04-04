import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends JFrame {
    public static void main(String[] args) {
        ParkingLot p = new ParkingLot();
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        try {
            Scanner fileIn = new Scanner(new File("student.txt"));

            if(fileIn != null) {
                while(fileIn.hasNext()) {
                    String line = fileIn.nextLine();
                    String data[] = line.split(",");

                    if(data.length > 4)
                        continue;

                    personList.add(new Person(data[0], data[1], data[2]));
                }

                fileIn.close();
            }

            fileIn = new Scanner(new File("vehicles.txt"));
            if(fileIn != null) {
                while(fileIn.hasNext()) {
                    String line = fileIn.nextLine();
                    String data[] = line.split(",");

                    if(data.length > 3)
                        continue;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for(Person s : personList) {
            for(Vehicle veh : vehicleList) {
                if (s.getMatricNo().equals(veh.getMatricno())) {
                    System.out.println(veh.getMatricno() + veh.getPlate());
                }
            }
        }
    }

    private static void DesignGUI() {
        JFrame frame = new JFrame();
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        frame.setTitle("FTSM Parking Lot");

        Container _container = frame.getContentPane();
        _container.setLayout(new FlowLayout());
        _container.setBackground(Color.BLUE);

        JButton btn1 = new JButton("Continue");
        JButton btn2 = new JButton("New");
        _container.add(btn1);
        _container.add(btn2);
    }

    private void mainDesign() {
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
    }
}
