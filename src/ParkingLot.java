public class ParkingLot {

    protected Parkable onLot;

    public ParkingLot() {
        this.onLot = null;
    }

    public void ParkVehicle(Parkable v) {
        this.onLot = v;
    }
}
