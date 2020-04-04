import java.awt.*;
import java.util.ArrayList;

public class Vehicle implements Parkable {
    private String matricno;
    private String plate;
    private boolean isPark;
    private String type;
    private ArrayList<Vehicle> vehicles;

    public Vehicle(String m, String p, String t) {
        this.matricno = m;
        this.plate = p;
        this.type = t;
        this.isPark = false;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getMatricno() {
        return matricno;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public boolean getState() {
        return false;
    }
}
