/**
 * A cargo ship, with a unique id, that arrives at
 * the space station to deliver its cargo.
 *
 * @author ngeard@unimelb.edu.au
 *  
 *  Zhijin Li 766041
 *
 */

public class Ship {

    // a unique identifier for this cargo ship
    private int id;

    // the next ID to be allocated
    private static int nextId = 1;

    // a flag indicating whether the ship is currently loaded
    boolean loaded;
    
    // the pilot who drives this ship
    private Pilot pilot = null;
    
    // the number of tugs that this ship has
    private int numTugs = 0;
    
    public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	// create a new vessel with a given identifier
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
    }

    // get a new Ship instance with a unique identifier
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }

    // produce an identifying string for the cargo ship
    public String toString() {
        return "ship [" + id + "]";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public int getNumTugs() {
		return numTugs;
	}

	public void setNumTugs(int numTugs) {
		this.numTugs = numTugs;
	}
}
