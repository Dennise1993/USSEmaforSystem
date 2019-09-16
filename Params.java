import java.util.Random;
/**
 * A class gathers various system-wide parameters for convenience,
 * 
 * @author ngeard@unimelb.edu.au
 * 
 * Zhijin Li 766041
 *
 */
class Params {
    static final int NUM_PILOTS = 2;

    static final int NUM_TUGS = 5;

    static final int DOCKING_TUGS = 3;

    static final int UNDOCKING_TUGS = 2;

    static final int DOCKING_TIME = 800;

    static final int UNDOCKING_TIME = 400;

    static final int UNLOADING_TIME = 1200;

    static final int TRAVEL_TIME = 800;

    //the time that shield is activated 
    static final int DEBRIS_TIME = 1800;
    
    // the number of parking spots in arrival wait zone
    static final int NUM_ARRIVAL_PARKING = 1;
    
    // the number of parking spots in departure wait zone
    static final int NUM_DEPARTURE_PARKING = 1;

    private static final int MAX_ARRIVAL_INTERVAL = 400;

    private static final int MAX_DEPARTURE_INTERVAL = 1000;

    private static final int MAX_DEBRIS_INTERVAL = 2400;

    // the time that the shield is not activated
    static int debrisLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEBRIS_INTERVAL);
    }

    // the time between two arrivals
    static int arrivalLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_ARRIVAL_INTERVAL);
    }

    // the time between two departures
    static int departureLapse() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_DEPARTURE_INTERVAL);
    }
}
