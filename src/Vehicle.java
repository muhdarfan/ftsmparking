public class Vehicle {
    private String plate;
    private String type;

    public Vehicle(String p, String t) {
        this.plate = p;
        this.type = t;
    }

    public String toString() {
        return String.format("%s,%s", this.plate, this.type);
    }

    public String getPlate() {
        return plate.toUpperCase();
    }

    public String getType() {
        return type.toLowerCase();
    }

    public boolean isPark() {
        for (ParkingLot pl : Core.parkingLotList) {
            for (Space sp : pl.getSpace()) {
                if (sp.getPlate().equals(this.plate))
                    return true;
            }
        }

        return false;
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