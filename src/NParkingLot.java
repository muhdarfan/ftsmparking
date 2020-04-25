import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class NParkingLot {
    Dictionary<Integer, ArrayList<Space>> Lots;

    public NParkingLot(int general, int motor, int staff, int dedicated) {
        this.Lots = new Hashtable<>();
    }
}
