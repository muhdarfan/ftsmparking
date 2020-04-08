public class Vehicle implements Parkable {
    private String plate;
    private boolean isPark;
    private String type;

    public Vehicle(String p, String t) {
        this.plate = p;
        this.type = t;
        this.isPark = false;
    }

    public String toString() {
        return String.format("%s,%s", this.plate, this.type);
    }

    public String getType() {
        return type;
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