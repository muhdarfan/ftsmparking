import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot {
    private static ArrayList<Space> _lots;

    public ParkingLot() {
        try {
            Scanner _file = new Scanner(new File("parking.txt"));

            if (_file != null) {
                while(_file.hasNextLine()) {
                    String line = _file.nextLine();
                    String[] data = line.split(",");

                    if (data.length == 2)
                        _lots.add(new Space(data[0], data[1]));
                }
            }
        } catch (Exception e) {}
    }

    private void FreshStart() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("parking.txt"));
        for(int i = 1; i <= 80; i++) {
            if (i <= 20)
                pw.printf("A%i,null\n", i);
            else if (i <= 40)
                pw.printf("B%i,null\n", (i-20));
            else if (i <= 60)
                pw.printf("C%i,null\n", (i-40));
            else if (i <= 80)
                pw.printf("D%i,null\n", (i-60));
        }
        pw.close();
    }

    public static boolean isPark(String plate) {
        for(Space s : _lots)
            if(s.getPlate() == plate)
                return true;

        return false;
    }

    public static void ParkVehicle(String id, Parkable v) {
        for (Space s : _lots)
            if (s.getPlate() == v.getPlate())
                s.onLot = v;
    }
}

class Space {
    private String id;
    private String category;
    private String plate;
    protected Parkable onLot;

    public Space(String i, String p) {
        this.plate = p;
        this.id = i;
        this.validate(i);
    }

    private void validate(String i) {
        char f = i.charAt(0);

        switch (f) {
            case 'A':
                this.category = "Student";
                break;

            case 'B':
                this.category = "Staff";
                break;

            case 'C':
                this.category = "Management";
                break;

            case 'D':
                this.category = "General";
                break;
        }
    }

    public String getPlate() {
        return plate;
    }

    public String getId() {
        return id;
    }
}