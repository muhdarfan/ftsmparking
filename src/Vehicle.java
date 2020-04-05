public class Vehicle implements Parkable {
    private String plate;
    private boolean isPark;
    private String type;

    public Vehicle(String p, String t) {
        this.plate = p;
        this.type = t;
        this.isPark = false;
    }

    @Override
    public String getPlate() {
        return this.plate;
    }
}
