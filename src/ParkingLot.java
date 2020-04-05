import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot {
    protected Parkable onLot;
    private static ArrayList<Space> _lots;

    public ParkingLot() {
        try {
            Scanner _file = new Scanner(new File("parking.txt"));

            if (_file != null) {
                while(_file.hasNextLine()) {
                    String line = _file.nextLine();
                    String[] data = line.split(",");

                }
            }
        } catch (Exception e) {}

    }

    public static boolean isPark(String plate) {
        for(Space s : _lots) {
            if(s.getPlate() == plate)
                return true;
        }

        return false;
    }

    public void ParkVehicle(Parkable v) {
        this.onLot = v;
    }
}

class Space {
    private int id;
    private String plate;

    public Space(int i, String p) {
        this.id = i;
        this.plate = p;
    }

    public String getPlate() {
        return plate;
    }

    public int getId() {
        return id;
    }
}