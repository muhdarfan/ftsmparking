public class Vehicle implements Parkable {
    private String plate;
    private String type;

    public Vehicle(String p, String t) {
        this.plate = p;
        this.type = t;
    }

    public String toString() {
        return String.format("%s,%s", this.plate, this.type);
    }

    public String getType() {
        return type;
    }

    public boolean isPark() {
        for (Space s : ParkingLot.getLots()) {
            if (s.getPlate().equals(this.plate))
                return true;
        }
        return false;
    }

    @Override
    public String getPlate() {
        return this.plate;
    }
}

class Car extends Vehicle {
    private String brand;
    private String model;

    public Car(String p) {
        super(p, "car");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String p) {
        super(p, "motorcycle");
    }
}

class Bus extends Vehicle {
    public Bus(String p) {super(p, "bus");}
}

class Heavy extends Vehicle {
    public Heavy(String p) {
        super(p, "heavy");
    }
}