public class Vehicle implements Parkable {
    private String plate;
    private boolean isPark;
    private String type;

    public Vehicle(String p, String t) {
        this.plate = p;
        this.type = t;
        this.isPark = false;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public boolean getState() {
        // Read dari file parking.txt
        // check whether vehicle plate is in parking.txt
        ParkingLot lot = new ParkingLot();
        return false;
    }
}
