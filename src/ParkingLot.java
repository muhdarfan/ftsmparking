import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot {
    private static ArrayList<Space> _lots;
    private final char symbol[] = {
      'A', // General
      'B', // Motor
      'C', // Staff
      'D'  // Dedicated
    };

    public ParkingLot(int gnrl, int motor, int staff, int dedicated) {
        try {
            _lots = new ArrayList<>();
            Read();
            if(!ValidateParking(gnrl, motor, staff, dedicated))
                throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            InitializeParking(gnrl, motor, staff, dedicated);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Read() throws FileNotFoundException {
        Scanner _file = new Scanner(new File("parking.txt"));

        if (_file != null) {
            while(_file.hasNextLine()) {
                String line = _file.nextLine();
                if(line.contains("#"))
                    continue;
                String[] data = line.split(",");

                if (data.length == 2)
                    _lots.add(new Space(data[0], data[1]));
            }
        }
    }

    private boolean ValidateParking(int g, int m, int s, int d) {
        int[] count = {0, 0, 0, 0};
        for (Space spc : _lots) {
            for (int i = 0; i < symbol.length; i++) {
                if(spc.getId().charAt(0) == symbol[i])
                    count[i]++;
            }
        }

        return (count[0] == g && count[1] == m && count[2] == s && count[3] == d);
    }

    private void InitializeParking(int g, int m, int s, int d) {
        try {
            int total = (g + m + s + d);

            PrintWriter pw = new PrintWriter(new File("parking.txt"));
            pw.println("##########\tPARKING DATA (ID, Plate)\t##########");
            for (int i = 1; i <= total; i++) {
                if (i <= g)
                    pw.printf("%c%d,null\n", symbol[0], i);
                else if (i <= m)
                    pw.printf("%c%d,null\n", symbol[1], (i - 20));
                else if (i <= s)
                    pw.printf("%c%d,null\n", symbol[2], (i - 40));
                else if (i <= d)
                    pw.printf("%c%d,null\n", symbol[3], (i - 60));
            }
            pw.close();
        } catch (Exception e) {System.out.println(e.getMessage());}
    }

    public static void ParkVehicle(String id, Parkable v) {
        for (Space s : _lots) {
            // Check if has vehicle at lot
            // Check category
            if(s.getId() == id.toUpperCase()) {
                s.plate = v.getPlate();
            }
        }
    }

    public static boolean isPark(String plate) {
        for(Space s : _lots)
            if(s.getPlate() == plate)
                return true;

        return false;
    }
}

class Space {
    private String id;
    private String category;
    protected String plate;
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
                this.category = "Motorcycle";
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

    public Parkable getOnLot() {
        return onLot;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }
}