import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test extends JFrame {
    private static ArrayList<Person> personList = new ArrayList<Person>();

    private static void removePerson(String matric) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getMatricNo() == matric) {
                personList.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Scanner fileIn = new Scanner(new File("person.txt"));

            if (fileIn != null) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine();
                    String data[] = line.split(",");

                    // Check data length. If data length is more than 4,
                    // then we need to skip it because this is not a valid data !
                    // Requirement:
                    if (data.length == 3)
                        personList.add(new Person(data[0], data[1], data[2]));
                    else
                        System.out.println("ADA MASALAH BANG: Data not valid :" + data[0]);
                }

                fileIn.close();
            }
        } catch (FileNotFoundException e) {
            String fileName = e.getMessage().substring(0, e.getMessage().indexOf(' '));
            System.out.println(fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FreshStart();
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
            if (newOwner == p) {
                p.getVehicles().add(temp);
                break;
            }
        }
    }

    private static void SaveData() throws Exception {
        PrintWriter pw = new PrintWriter(new File("person.txt"));
        PrintWriter pw1 = new PrintWriter(new File("vehicles.txt"));
        pw.write("############### STUDENT DATA ###############\n");
        pw1.write("############### VEHICLES DATA ###############\n");

        for (Person p : personList) {
            // Save person at person.txt
            pw.println(p.toString());

            // Save vehicles
            if (p.getVehicles().size() > 0) {
                for (Vehicle v : p.getVehicles())
                    pw1.printf("%s,%s\n", p.getMatricNo(), v.toString());
            }
        }
        pw.close();
        pw1.close();
    }

    private static void FreshStart() {
        System.out.println("WELCOME TO FTSM PARKING LOT!");

        try {
            String cmd = "";
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("\n\nMENU" +
                        "\n1 - Create new person" +
                        "\n2 - Create new vehicle" +
                        "\n3 - List person" +
                        "\n4 - List vehicle" +
                        "\n5 - Remove person" +
                        "\n6 - Remove vehicle" +
                        "\n7 - Save data to files" +
                        "\n10 - Exit");

                System.out.print("Please choose an option: ");
                cmd = sc.next();

                switch (cmd) {
                    // Add people
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
                                } while (temp4 != 1 || temp4 != 2);
                                break;
                        }
                        break;
                    // Add Vehicle
                    case "2":
                        if (personList.size() > 0) {
                            int choice = -1;
                            boolean flag = true;
                            do {
                                try {
                                    for (int i = 1; i <= personList.size(); i++)
                                        System.out.printf("%d - %s\n", i, personList.get(i - 1).getName());

                                    System.out.print("Please choose a person that you want to associate the vehicle with: ");
                                    choice = (sc.nextInt() - 1);

                                    if (personList.get(choice).getName() != "")
                                        flag = false;
                                } catch (Exception e) {
                                }
                            } while (flag);

                            Person tempPerson = personList.get(choice);
                            flag = true;

                            System.out.println("\nPlease enter vehicle information...");
                            do {
                                System.out.println("1 - Car\n2 - Motorcycle\n3 - Heavy");
                                System.out.print("Vehicle type: ");
                                choice = sc.nextInt();

                                if (choice == 1 || choice == 2 || choice == 3)
                                    flag = false;
                            } while (flag);

                            System.out.print("\nPlease enter vehicle plate: ");
                            sc.nextLine();
                            String tempPlate = sc.nextLine();

                            tempPerson.addVehicle(tempPlate, choice);
                        } else {
                            System.out.println("There are no person yet.");
                        }
                        break;
                    // List person
                    case "3":
                        if (!personList.isEmpty()) {
                            int count = 1;
                            for (Person p : personList) {
                                System.out.printf("\n%d => %s", count, p.toString());
                                count++;
                            }
                        } else {
                            System.out.println("There's no people yet ! =<");
                        }
                        break;

                    // List vehicle
                    case "4":
                        if (!personList.isEmpty()) {
                            int count = 0;
                            for (Person p : personList) {
                                if (!p.getVehicles().isEmpty()) {
                                    for (Vehicle v : p.getVehicles()) {
                                        System.out.printf("\n%d => %s - %s [%s]", ++count, v.getType(), v.getPlate(), p.getName());
                                    }
                                }
                            }
                        } else {
                            System.out.println("There's no people yet ! =<");
                        }
                        break;

                    // Remove person
                    case "5":
                        if (personList.size() > 0) {
                            boolean flag = true;
                            int choice = -1;
                            do {
                                try {
                                    for (int i = 1; i <= personList.size(); i++)
                                        System.out.printf("%d - %s\n", i, personList.get(i - 1).getName());

                                    System.out.print("Please enter the index number for the person: ");
                                    choice = (sc.nextInt() - 1);

                                    if (personList.get(choice).getName() != "")
                                        flag = false;
                                } catch (Exception e) {
                                }
                            } while (flag);

                            personList.remove(choice);
                        } else {
                            System.out.println("There's no people yet ! =<");
                        }
                        break;

                    // Remove vehicle
                    case "6":
                        if (personList.size() > 0) {
                            boolean flag = true;
                            int choice = -1, i = 0;

                            do {
                                try {
                                    for (Person p : personList) {
                                        if (!p.getVehicles().isEmpty()) {
                                            i = 0;

                                            System.out.printf("%d - %s [%d vehicles]\n", ++i, p.getName(), p.getVehicles().size());
                                        }
                                    }
                                    System.out.print("Please enter the index number for the person: ");
                                    choice = (sc.nextInt() - 1);

                                    if (personList.get(choice).getName() != "") {
                                        for (Vehicle v : personList.get(choice).getVehicles()) {
                                            i = 0;
                                            System.out.printf("%d - %s [%s]\n", ++i, v.getPlate(), v.getType());
                                        }
                                        System.out.print("Please enter the index number for the vehicle: ");
                                        int idx = sc.nextInt() - 1;

                                        if (personList.get(choice).getVehicles().get(idx).getPlate() != "") {
                                            flag = false;
                                            personList.get(choice).getVehicles().remove(idx);
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            } while (flag);
                        } else {
                            System.out.println("There's no people associated with vehicle yet ! =<");
                        }
                        break;

                    // Save data to files
                    case "7":
                        if (personList.size() > 0) {
                            SaveData();
                        } else {
                            System.out.println("There's no people need to be saved !");
                        }
                        break;

                    default:
                        break;
                }
            } while (Integer.parseInt(cmd) != 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
