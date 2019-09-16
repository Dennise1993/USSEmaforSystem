/**
 * Tug controller, whose job is to receive requests from pilots and 
 * allocate the requested number of tugs once they are available.
 * 
 * @author Zhijin Li 766041
 * 
 */
public class Tugs {
	
	// the number of tugs in tug controller
	volatile private int numTugs;

	public Tugs(int numTugs) {
		this.numTugs = numTugs;
	}
	
	// pilot releases tugs
	synchronized public void returnTugs(int num, Pilot pilot) {
		// update the current number of tugs for the ship after returning tugs
		int leftTugs = pilot.getShip().getNumTugs() - num;
		pilot.getShip().setNumTugs(leftTugs);
		
		numTugs += num;		
		
		System.out.println(pilot.toString() + " releases " + num 
				+ " tugs. (" + numTugs + " available).");		
		
		notifyAll(); // tell pilots tugs are available

	}
	
	// pilot acquires specific number of tugs
	synchronized public void acquireTugs(int num, Pilot pilot) {
		while(numTugs < num) {
			// there is no tug, tell pilots to wait
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		// update the current number of tugs for the ship after acquiring tugs
		int newTugs = pilot.getShip().getNumTugs() + num;
		pilot.getShip().setNumTugs(newTugs);
		
		numTugs -= num;
		
		System.out.println(pilot.toString() + " acquires " + num 
				+ " tugs. (" + numTugs + " available).");
		
	}

}
