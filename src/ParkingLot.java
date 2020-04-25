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
    // Test
    private static int count[];

    public ParkingLot(int gnrl, int motor, int staff, int dedicated) {
        try {
            _lots = new ArrayList<>();
            count = new int[]{
                    gnrl,
                    motor,
                    staff,
                    dedicated
            };

            Read();
            if (!ValidateParking(gnrl, motor, staff, dedicated))
                throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            InitializeParking(gnrl, motor, staff, dedicated);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public char[] getSymbol() {
        return symbol;
    }

    public static ArrayList<Space> getLots() {
        return _lots;
    }

    private void Read() throws FileNotFoundException {
        Scanner _file = new Scanner(new File("parking.txt"));

        if (_file != null) {
            while (_file.hasNextLine()) {
                String line = _file.nextLine();
                if (line.contains("#"))
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
                if (spc.getBlock() == symbol[i])
                    count[i]++;
            }
        }

        return (count[0] == g && count[1] == m && count[2] == s && count[3] == d);
    }

    private void InitializeParking(int g, int m, int s, int d) {
        try {
            int total = (g + m + s + d);

            PrintWriter pw = new PrintWriter(new File("parking.txt"));
            pw.println("##########\tPARKING DATA (ID, Plate) [Total: " + total + "]\t##########");
            int symCount = 0;
            for (int i : count) {
                for (int j = 1; j <= i; j++) {
                    pw.printf("%c%d,null\n", symbol[symCount], j);
                }
                symCount++;
            }
            /*
            for (int i = 1; i <= total; i++) {
                if (i <= g) // 0 <= 20
                    pw.printf("%c%d,null\n", symbol[0], i);
                else if (i <= (m + 20)) // 20 < i < 40
                    pw.printf("%c%d,null\n", symbol[1], (i - 20));
                else if (i <= (s + 40))
                    pw.printf("%c%d,null\n", symbol[2], (i - 40));
                else if (i <= (d + 60))
                    pw.printf("%c%d,null\n", symbol[3], (i - 60));
            }
             */
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Space getSpace(String x) {
        if(x.length() > 1) {
            char block = x.charAt(0);
            int id = Integer.parseInt(x.substring(1));

            for (Space s : getLots()) {
                if (s.getId() == id && block == s.getBlock())
                    return s;
            }
        }

        return null;
    }

    public static void printLots() {
        int current = 0;
        for (int i : count) {
            int row = (int) Math.ceil(i / (float) 5);

            // Row
            for (int r = 1; r <= row; r++) {
                // Column
                StringBuilder rowText = new StringBuilder();
                for (int j = (r * 5) - 5; j < (r * 5) && j < i; j++) {
                    Space sp = ParkingLot.getLots().get(current);

                    rowText.append(String.format("%s%s%s ", (sp.plate.equals("null") ? "\u001B[32m" : "\u001B[31m"), (sp.plate.equals("null") ? sp.getFull() : sp.getPlate()), "\u001B[0m"));
                        /*
                        #### ^ Equivalence

                        if(sp.plate.equals("null")) {
                            rowText.append(String.format("%s%s%s ", ANSI_GREEN, sp.getFull(), ANSI_RESET));
                        } else {
                            rowText.append(String.format("%s%s%s ", ANSI_RED, sp.getPlate(), ANSI_RESET));
                        }
                        */
                    current++;
                }
                System.out.println(rowText.toString().substring(0, rowText.toString().length() - 1));
            }
        }
    }

    public static boolean ParkVehicle(String usercategory, Space lot, Parkable v) {
        int idx = _lots.indexOf(lot);
        Space s = _lots.get(idx);

        if (s.plate.equals("null")) {
            _lots.get(idx).setPlate(v.getPlate());
            _lots.set(idx, s);
            SaveData();
            return true;
        }

        return false;
    }

    public static boolean isAuthorized(String category, Space s) {
        //

        return false;
    }

    public static boolean SaveData() {
        try {
            PrintWriter pw = new PrintWriter(new File("parking.txt"));
            pw.println("##########\tPARKING DATA (ID, Plate) [Total: " + _lots.size() + "]\t##########");

            for (Space s : getLots())
                pw.println(s.toString());

            pw.close();
            return true;
        } catch (Exception e) {

        }

        return false;
    }

    public static boolean isPark(String plate) {
        for (Space s : _lots)
            if (s.getPlate() == plate)
                return true;

        return false;
    }
}

class Space {
    private int id;
    private char block;
    private String category;
    protected String plate;
    protected Parkable onLot;

    public Space(String i, String p) {
        this.plate = p;
        this.id = Integer.parseInt(i.substring(1));
        this.block = i.charAt(0);
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

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setOnLot(Parkable onLot) {
        this.onLot = onLot;
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

    public String getFull() {
        return String.format("%c%02d", this.block, this.id);
    }

    public String toString() { return String.format("%s,%s", this.getFull(), (this.getPlate().isEmpty() ? "null" : this.getPlate())); }

    public int getId() {
        return id;
    }

    public char getBlock() {
        return block;
    }
}