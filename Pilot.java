/**
 * Pilot who is responsible to drive ship from 
 * arrival wait zone to departure wait zone.
 * 
 * @author Zhijin Li 766041
 *
 */
public class Pilot extends Thread{
		
	private int i;	// index of pilot
	private ArrivalWaitZone arrivalZone;
	private DepartWaitZone departureZone;
	private Tugs tugs;
	private Berth berth;
	
	private Ship ship = null;  // the ship that pilot drives
	
	public Pilot(int i, ArrivalWaitZone arrivalZone, 
			DepartWaitZone departureZone, Tugs tugs, Berth berth) {
		this.i = i;
		this.arrivalZone = arrivalZone;
		this.departureZone = departureZone;
		this.tugs = tugs;
		this.berth = berth;
	}
	
	/*
	 * acquires a newly arrived cargo ship, acquires the required number 
	 * of tugs to dock the ship.
	 * After the ship is unloaded, acquires tugs for undocking.
	 */	
	public void run() {
		
		// if pilot doesn't have a ship
		while(!isInterrupted() && ship == null) {
			// acquire ship
			arrivalZone.acquireShip(this);
			// acquire docking's tugs
			tugs.acquireTugs(Params.DOCKING_TUGS, this);	
			// depart arrival wait zone
			arrivalZone.depart(this);
			try {
				sleep(Params.TRAVEL_TIME);
				// dock in berth
				berth.dock(ship);
				
				sleep(Params.DOCKING_TIME);
				
			} catch (InterruptedException e) {
				this.interrupt();
			}
			
			// complete docking and release docking's tugs
			tugs.returnTugs(Params.DOCKING_TUGS, this);
						
			// being unloaded
			try {
				System.out.println(ship.toString() + " being unloaded.");
				sleep(Params.UNLOADING_TIME);
				ship.setLoaded(false);
			} catch (InterruptedException e) {
				this.interrupt();
			}
			
			// acquire undocking's tugs
			tugs.acquireTugs(Params.UNDOCKING_TUGS, this);
			// commence undocking
			berth.undock(ship);
			
			try {
				sleep(Params.UNDOCKING_TIME + Params.TRAVEL_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// arrive at departure wait zone
			departureZone.arrive(ship);
			
			// release undocking's tugs
			tugs.returnTugs(Params.UNDOCKING_TUGS, this);
			
			// depart wait zone
			departureZone.depart();
		}
				
	}
	
    // produce an identifying string for the pilot
    public String toString() {
        return "pilot [" + i + "]";
    }

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public ArrivalWaitZone getArrivalZone() {
		return arrivalZone;
	}

	public void setArrivalZone(ArrivalWaitZone arrivalZone) {
		this.arrivalZone = arrivalZone;
	}

	public DepartWaitZone getDepartureZone() {
		return departureZone;
	}

	public void setDepartureZone(DepartWaitZone departureZone) {
		this.departureZone = departureZone;
	}

	public Tugs getTugs() {
		return tugs;
	}

	public void setTugs(Tugs tugs) {
		this.tugs = tugs;
	}

	public Berth getBerth() {
		return berth;
	}

	public void setBerth(Berth berth) {
		this.berth = berth;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}



}
