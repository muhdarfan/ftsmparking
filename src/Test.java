import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends JFrame {
    private static ArrayList<Person> personList = new ArrayList<Person>();

    private static void removePerson(String matric) {
        for(int i =0;i<personList.size();i++) {
            if(personList.get(i).getMatricNo() == "A174652") {
                personList.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        /*
        0 - General (30)
        1 - Staff (20)
        2 - Student (20)
        3 - Management (10)
         */

        try {
            Scanner fileIn = new Scanner(new File("students.txt"));

            if (fileIn != null) {
                // Muhammad Nur Faris bin Kaman , A174652 , Staff
                // data[0], data[1], data[2]

                while (fileIn.hasNext()) {
                    String line = fileIn.nextLine();
                    String data[] = line.split(",");

                    // Check data length. If data length is more than 4,
                    // then we need to skip it because this is not a valid data !
                    // Requirement:
                    if (data.length == 3)
                        personList.add(new Person(data[0], data[1], data[2]));
                    else
                        throw new Exception("ADA MASALAH BANG: Data not valid " + data[0]);
                }

                fileIn.close();
            }
        } catch (FileNotFoundException e) {
            String fileName = e.getMessage().substring(0, e.getMessage().indexOf(' '));
            System.out.println(fileName);

            FreshStart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void FreshStart() {
        System.out.println("WELCOME TO FTSM PARKING LOT!\n");

        boolean flag = true;
        String cmd = "";
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1 - Create new person\n2 - Create new vehicle\n3 - Exit");
            System.out.print("Please choose an option: ");
            cmd = sc.next();

            switch (cmd) {
                case "1":
                    // get name, get matric no
                    System.out.print("Name: ");
                    sc.nextLine();
                    String temp = sc.nextLine();
                    System.out.print("Matric no: ");
                    String temp2 = sc.next().toUpperCase();
                    char temp3 = temp2.charAt(0);

                    switch (temp3) {
                        case 'A':
                            personList.add(new Student(temp, temp2));
                            break;

                        case 'S':
                            int temp4;
                            do {
                                System.out.println("1 - Ordinary\n2 - Management");
                                System.out.print("It seems like your matric is associated with staff. Please choose: ");

                                temp4 = sc.nextInt();

                                personList.add(new Staff(temp, temp2, (temp4 == 2 ? "Management" : "Ordinary")));
                            } while(temp4 != 1 || temp4 != 2);
                            break;
                    }
                    break;

                case "2":
                    if(personList.size() > 0) {

                    } else {
                        System.out.println("No person to associate the vehicle with.");
                    }
                    break;

                default:
                    continue;
            }
        } while (Integer.parseInt(cmd) != 3);
        /*
        do {
            try {
                int temp = sc.nextInt();

                do {
                    System.out.println("Please choose an option:");
                    System.out.println("1 - Create new person\n2 - Create new vehicle\n3 - Exit");
                }
                while (temp )
            } catch (Exception e) {

            }
        } while (flag);
         */
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
