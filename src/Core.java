import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Core {
    public static ArrayList<Person> personList;
    public static ArrayList<ParkingLot> parkingLotList;

    public Core() {
        this.personList = new ArrayList<>();
        this.parkingLotList = new ArrayList<>();

        this.LoadPerson();
        this.LoadParking();
    }

    public static boolean FreshStart() {
        try {
            // Initialize Parking
            parkingLotList.clear();
            personList.clear();

            parkingLotList.add(new ParkingLot("General", 'A', "general", 20));
            parkingLotList.add(new ParkingLot("Motorcycle", 'B', "motorcycle", 13));
            parkingLotList.add(new ParkingLot("Staff", 'C', "staff", 15));
            parkingLotList.add(new ParkingLot("Manager", 'D', "dedicated", 10));

            Core.SavePerson();
            Core.SaveParking();
            LoadPerson();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean LoadPerson() {
        try {
            Scanner fileIn = new Scanner(new File("person.txt"));

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                if (line.contains("#"))
                    continue;

                String data[] = line.split(",");

                if (data.length == 3)
                    personList.add(new Person(data[0], data[1], data[2].toLowerCase()));
            }

            fileIn.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            ShowErrorMessage(e.getMessage(), "LoadPerson");
        }

        return false;
    }

    public static boolean LoadParking() {
        try {
            Scanner fileIn = new Scanner(new File("parking_data.txt"));
            boolean check = true;

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                if (line.contains("PARKING DATA"))
                    check = true;
                if (line.contains("SPACE")) {
                    check = false;
                }

                if (line.contains("#"))
                    continue;

                String[] data = line.split(",");
                // For Parking Lot Data
                // {block},{name},{category}
                if (check && data.length == 3) {
                    parkingLotList.add(new ParkingLot(data[1], data[0].charAt(0), data[2]));
                } else if (!check && data.length == 3) { // For Space Data
                    // {block},{id},{vehicle}
                    for (ParkingLot pl : parkingLotList) {
                        if (pl.getBlock() == data[0].charAt(0)) {
                            pl.getSpace().add(new Space(data[0].charAt(0), Integer.parseInt(data[1]), data[2]));
                            break;
                        }
                    }
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean SavePerson() {
        try {
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
        } catch (Exception e) {
        }

        return false;
    }

    public static boolean SaveParking() {
        try {
            if (parkingLotList.size() > 0) {
                PrintWriter pw = new PrintWriter(new File("parking_data.txt"));

                for (ParkingLot pl : parkingLotList) {
                    // {block},{name},{category}
                    pw.println("######## PARKING DATA ########");
                    pw.println(pl.toString());

                    if (pl.getSpace().size() > 0) {
                        pw.println("######## SPACE DATA ########");

                        for (Space s : pl.getSpace()) {
                            // {block},{id},{vehicle}
                            pw.println(s.toString());
                        }
                    }
                    pw.println();
                }

                pw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * PERSON
     **/
    public static boolean AddPerson(Person p) {
        boolean found = false;
        for (Person xP : personList)
            if (xP.getMatricNo().equals(p.getMatricNo()))
                found = true;

        if (!found) {
            personList.add(p);
            SavePerson();
            return true;
        }

        return false;
    }

    public static boolean RemovePersonByMatric(String matric) {
        for (Person p : personList) {
            if (p.getMatricNo().equals(matric)) {
                personList.remove(p);
                SavePerson();
                return true;
            }
        }

        return false;
    }

    public static Person GetPersonByMatric(String matric) {
        for (Person p : personList)
            if (p.getMatricNo().equals(matric))
                return p;

        return null;
    }


    public static Person GetPersonByPlate(String plate) {
        for (Person p : personList)
            for (Vehicle v : p.getVehicles())
                if (v.getPlate().equals(plate))
                    return p;

        return null;
    }

    /**
     * VEHICLES
     **/
    public static void ChangeVehicleOwnership(String plate, Person newOwner) {
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

        SavePerson();
    }

    public static Vehicle GetVehicleByPlate(String plate) {
        for (Person p : personList)
            for (Vehicle v : p.getVehicles())
                if (v.getPlate().equals(plate))
                    return v;

        return null;
    }

    public static ArrayList<Vehicle> GetVehicles(boolean onlyNotPark) {
        ArrayList<Vehicle> list = new ArrayList<>();

        for (Person p : personList)
            for (Vehicle v : p.getVehicles())
                if ((onlyNotPark && !v.isPark()) || (!onlyNotPark))
                    list.add(v);

        return list;
    }

    public static ArrayList<Vehicle> GetVehicles(ParkingLot lots, boolean onlyNotPark) {
        ArrayList<Vehicle> list = new ArrayList<>();

        for (Person p : personList)
            for (Vehicle v : p.getVehicles())
                if (
                        (!v.getType().equals("heavy")) &&
                        ((lots.canPark(p.getCategory()) || lots.canPark(v.getType())) &&
                        ((onlyNotPark && !v.isPark()) || (!onlyNotPark))) ||
                        (p.getCategory().equals("management"))
                )
                    list.add(v);

        return list;
    }

    public static boolean RemoveVehicle(String plate, String matric) {
        for (Person p : personList) {
            for (Vehicle v : p.getVehicles()) {
                if (p.getMatricNo().equals(matric) && v.getPlate().equals(plate)) {
                    // Remove from parking
                    for (ParkingLot pl : parkingLotList)
                        if (pl.UnparkVehicle(v))
                            break;

                    p.getVehicles().remove(v);
                    SavePerson();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * PARKING LOT
     */
    public static ParkingLot GetParkingLot(char block) {
        for (ParkingLot pl : parkingLotList) {
            if (pl.getBlock() == block)
                return pl;
        }

        return null;
    }

    public static ArrayList<Space> GetParkingSpaceByBlock(char block) {
        for (ParkingLot pl : parkingLotList) {
            if (pl.getBlock() == block)
                return pl.getSpace();
        }

        return null;
    }

    public static ArrayList<ParkingLot> GetAvailableParkingLot(Person p, String category) {
        ArrayList<ParkingLot> parkingLot = new ArrayList<>();

        for (ParkingLot pl : parkingLotList) {
            if (!category.equals("heavy") && (pl.canPark(category) || pl.canPark(p.getCategory())) ||  p.getCategory().equals("management"))
                parkingLot.add(pl);
        }

        return parkingLot;
    }

    public static void ShowErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, "FTSM Parking Lot " + title, JOptionPane.ERROR_MESSAGE);
    }
}
