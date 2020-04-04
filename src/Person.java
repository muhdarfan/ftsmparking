import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private String name;
    private String matricNo;
    private String category;
    private ArrayList<Vehicle> vehicles;

    public Person(String n, String m, String c) {
        this.name = n;
        this.matricNo = m;
        this.category = c;
    }

    private void Init() {
        try {
            Scanner filein = new Scanner(new File("vehicles.txt"));

            if (filein != null) {
                while (filein.hasNext()) {
                    String line = filein.nextLine();
                    String[] data = line.split(",");

                    if (data.length > 3)
                        continue;

                    switch (data[2]) {
                        case "1":
                            vehicles.add(new Motorcycle(data[0], data[1]));
                            break;

                        case "2":
                            vehicles.add(new Car(data[0], data[1]));
                            break;

                        default:
                            vehicles.add(new Vehicle(data[0], data[1], data[2]));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    // Setter method
    public void setName(String name) {
        this.name = name;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter method
    public String getName() {
        return name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public String getCategory() {
        return category;
    }
}
