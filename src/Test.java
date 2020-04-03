import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
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

                    if(data.length > 2)
                        continue;

                    vehicleList.add(new Vehicle(data[0], data[1]));
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
}
