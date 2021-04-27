import java.util.ArrayList;

public class ParkingLot {
    private char block;
    private String name;
    private String authorizedCategory;
    private ArrayList<Space> space;

    public ParkingLot(String name, char b, String category) {
        this.block = b;
        this.name = name;
        this.authorizedCategory = category;
        this.space = new ArrayList<>();
    }

    public ParkingLot(String name, char b, String category, int count) {
        this.block = b;
        this.name = name;
        this.authorizedCategory = category;
        this.space = new ArrayList<>();

        for (int i = 0; i < count;i++)
            this.space.add(new Space(this.block, (i+1), "null"));
    }

    public boolean canPark(String category) {
        if (!category.equals("heavy") && (category.equals(this.authorizedCategory) || this.authorizedCategory.equals("general") || this.authorizedCategory.equals("null")))
            return true;

        return false;
    }

    public boolean ParkVehicle(int id, Vehicle v) {
        Space xSpace = this.space.get(id);

        if (xSpace.isEmpty()) {
            xSpace.plate = v.getPlate();
            Core.SaveParking();
            return true;
        }

        return false;
    }

    public boolean UnparkVehicle(Vehicle v) {
        for (Space sp : this.space) {
            if(sp.getPlate().equals(v.getPlate())) {
                sp.plate = "null";
                Core.SaveParking();
                return true;
            }
        }

        return false;
    }

    public void UnparkVehicle(int id) {
        Space xSpace = this.space.get(id);

        xSpace.plate = "null";
        Core.SaveParking();
    }

    public String getName() {
        return name;
    }

    public char getBlock() {
        return block;
    }

    public ArrayList<Space> getSpace() {
        return space;
    }

    public String getCategory() {
        return authorizedCategory;
    }

    // {block},{name},{category}
    public String toString() {
        return String.format("%c,%s,%s", this.block, this.name, this.authorizedCategory);
    }
}

class Space {
    private int id;
    private char block;
    protected String plate;

    public Space(char b, int i, String p) {
        this.id = i;
        this.block = b;
        this.plate = p;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String toString() {
        //// {block},{id},{vehicle}
        return String.format("%c,%d,%s", this.block, id, (this.getPlate().isEmpty() ? "null" : this.getPlate()));
    }

    public int getId() {
        return id;
    }

    public char getBlock() {
        return block;
    }

    public String getPlate() {
        return plate;
    }

    public String getFull() {
        return String.format("%c%02d", this.block, this.id);
    }

    public boolean isEmpty() {
        if (this.plate.equals("null"))
            return true;

        return false;
    }
}