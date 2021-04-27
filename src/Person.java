import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        this.vehicles = new ArrayList<>();

        Init();
    }

    private void Init() {
        try {
            Scanner filein = new Scanner(new File("vehicles.txt"));

            while (filein.hasNextLine()) {
                String line = filein.nextLine();
                String[] data = line.split(",");

                if (data.length == 3)
                    if (data[0].toLowerCase().equals(this.matricNo.toLowerCase()))
                        this.addVehicle(data[1], data[2]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addVehicle(String plate, String type) {
        plate = plate.toUpperCase();
        type = type.toLowerCase();

        switch (type) {
            case "car":
                this.vehicles.add(new Car(plate));
                break;

            case "motorcycle":
                this.vehicles.add(new Motorcycle(plate));
                break;

            case "heavy":
                this.vehicles.add(new Heavy(plate));
                break;

            default:
                this.vehicles.add(new Vehicle(plate, type));
                break;
        }
    }

    public void addVehicle(String plate, int type) {
        switch (type) {
            case 1:
                this.addVehicle(plate, "car");
                break;

            case 2:
                this.addVehicle(plate, "motorcycle");
                break;

            case 3:
                this.addVehicle(plate, "heavy");
                break;

            default:
                this.addVehicle(plate, "undefined");
                break;
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
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
        return category.toLowerCase();
    }

    public String toString() {
        return String.format("%s,%s,%s", this.name, this.matricNo, this.category);
    }

    public boolean isStaff() {
        if (this.category.equals("staff") || this.category.equals("management"))
            return true;
        return false;
    }
}