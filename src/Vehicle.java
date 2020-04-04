import java.awt.*;
import java.util.ArrayList;

public class Vehicle implements Parkable {
    private String matricno;
    private String plate;
    private boolean isPark;

    public Vehicle(String m, String p) {
        this.matricno = m;
        this.plate = p;
        this.isPark = false;
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
