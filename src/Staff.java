public class Staff extends Person {
    private boolean hasAppointment = false;

    public Staff(String n, String m, String c) {
        super(n, m, c);
    }

    public void setAppointment(boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public boolean isHasAppointment() {
        return hasAppointment;
    }
}
