import java.util.LinkedList;
/**
 * Departure wait zone where cargo ships driven into by pilot 
 * is consumed by consumer.
 * 
 * @author Zhijin Li 766041
 *
 */
public class DepartWaitZone {
	
	// ships that departure wait zone holds
	volatile private LinkedList<Ship> ships = new LinkedList<Ship>();
	// the number of parking spots of the departure zone 
	private int numParkingSpots;
	
	public DepartWaitZone(int parkingSpots) {
		this.numParkingSpots = parkingSpots;
	}

	// pilot puts ship in the wait zone
	synchronized public void arrive(Ship ship) {
		/*
		 *  check if a ship has tugs for undocking  
		 *  before it enters into departure wait zone
		 */
		while(ship.getNumTugs() != Params.UNDOCKING_TUGS) {
			System.out.println("ERROR---Ship has wrong number of tugs "
					+ "before going to the departure wait zone.");
			return;
		}
		
		while(ships.size() == numParkingSpots) {
			/*
			 *  there is no available spot, tell pilots to wait 
			 *  outside of the wait zone
			 */
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		ships.addLast(ship);
		System.out.println(ship.getPilot().toString() 
				+ " releases " + ship.toString() + ".");	
	}
	
	

	// consumer gets ship out of wait zone
	synchronized public void depart() {		
		// a flag used to check if pilot has released undocking's tugs
		boolean isError = true;
		
		while(ships.size() == 0) {
			// there is no ship in wait zone, tell consumer to wait
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		
		// find a ship that has released undocking's tugs 
		for (Ship ship: ships) {
			if(ship.getNumTugs() == 0) {
				isError = false;
				ship.getPilot().setShip(null);
				ship.setPilot(null);
				
				// consumer takes ship out of wait zone
				System.out.println(ship.toString() 
						+ " departs departure zone.");
				ships.remove(ship);				
				// tell other pilots there is a available spot, park in
				notifyAll();
			}

		}	
		
		if(isError) {
			System.out.println("ERROR---ship's tugs must be released "
					+ "before departure.");
			return;
		}
	}
	
	synchronized public LinkedList<Ship> getShips() {
		return ships;
	}

	synchronized public void setShips(LinkedList<Ship> ships) {
		this.ships = ships;
	}

}
