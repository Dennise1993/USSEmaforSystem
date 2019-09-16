/**
 * Berth used to hold one ship from departure wait zone 
 * so that the ship can be unloaded
 * 
 * @author Zhijin Li 766041
 *
 */
public class Berth {
	
	private String name;
	// a flag indicating if the berth has been occupied by a ship
	volatile private boolean hasOccupied = false;
	// a flag indicating if the shield has been activated to protect the berth
	volatile private boolean hasActivatedShield = false;

	public Berth(String name) {
		this.name = name;
	}
	
	// ship docks in the berth
	synchronized public void dock(Ship ship) {
		// check if ship is ready for docking
		while(!ship.isLoaded() || ship.getNumTugs() != Params.DOCKING_TUGS) {
			System.out.println("ERROR--- Ship is not loaded or has wrong "
					+ "number of tugs before docking.");
			return;
		}
		
		// if shield is activated or the berth is occupied, tell pilots to wait
		while(hasActivatedShield || hasOccupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
		// otherwise commence docking
		System.out.println(ship.toString() + " docks at berth.");
		hasOccupied = true; // mark that berth has been occupied
				
	}
	
	// ship undocks form berth
	synchronized public void undock(Ship ship) {
		//check if ship is ready for undocking
		while(!hasOccupied || ship.isLoaded() 
				|| ship.getNumTugs() != Params.UNDOCKING_TUGS) {
			/*
			 *  in case a developer calls methods in wrong order in Main class
			 */
			System.out.println("ERROR--- There is no ship in the berth,"
					+ "or the ship is still loaded, "
					+ "or ship has wrong number of tugs before undocking.");
			return;
		}
		
		// if shield is activated, tell pilots to wait
		while(hasActivatedShield) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// otherwise, undock from the berth
		System.out.println(ship.toString() + " undocks from " + name + ".");
		
		hasOccupied = false; // mark that berth has no ship
		notifyAll(); // tell other pilots to dock		
	}

	// activate the shield to protect shield from debris
	synchronized public void activateShield() {
		hasActivatedShield = true;
		System.out.println("Shield is activated.");
	}

	// deactivate the shield after debris time
	synchronized public void deactivateShield() {
		hasActivatedShield = false;
		System.out.println("Shield is deactivated.");
		// tell other pilots shield is deactivated
		notifyAll(); 
		
	}


	

}
