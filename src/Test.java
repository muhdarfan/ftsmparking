import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
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
        try {
            Scanner fileIn = new Scanner(new File("students.txt"));

            if (fileIn != null) {
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

    private static void ChangeVehicleOwnership(String plate, Person newOwner) {
        Vehicle temp = null;

        // Check whether the newOwner is in `personlist` array or not.
        // if not, then we return;
        if (!personList.contains(newOwner)) {
            System.out.println("owner is ghost (?)");
            return;
        }

        // Loop to get the old owner object and remove the vehicle from the person
        for (Person p : personList) {
            for (Vehicle v : p.getVehicles()) {
                if (v.getPlate() == plate) {
                    temp = v; // Get Attribute for the vehicles
                    p.getVehicles().remove(v);
                    break;
                }
            }
        }

        // Loop to get the new owner object.
        // Then add the vehicle to vehicles attribute for the person.
        for (Person p : personList) {
            if(newOwner == p) {
                p.getVehicles().add(temp);
                break;
            }
        }
    }

    private static void SaveData() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("student.txt"));
        pw.write("############### STUDENT DATA ###############");
        for (Person p : personList) {
            // Save person at person.txt
            pw.println(p.toString());
        }
        pw.close();
    }

    private static void FreshStart() {
        System.out.println("WELCOME TO FTSM PARKING LOT!");

        String cmd = "";
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n\n1 - Create new person\n2 - Create new vehicle\n3 - List person\n6 - Exit");
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

                case "3":
                    if (personList.size() > 0) {
                        int count = 1;
                        for (Person p : personList) {
                            System.out.printf("%d => %s\n", count, p.toString());
                            count++;
                        }
                    } else {
                        System.out.println("There's no people yet ! =<");
                    }
                    break;

                default:
                    break;
            }
        } while (Integer.parseInt(cmd) != 6);
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
