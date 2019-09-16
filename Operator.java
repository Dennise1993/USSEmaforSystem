/**
 * A class periodically activates the shield to protect the space 
 * station from space debris.
 * 
 * @author Zhijin Li 766041
 *
 */
public class Operator extends Thread {
	
	Berth berth;

	public Operator(Berth berth) {
		this.berth = berth;
	}
	
	// periodically activates the shield
	public void run() {
		while(!isInterrupted()) {
			try {
				// wait some time before the shield is activated
				sleep(Params.debrisLapse());
				
				berth.activateShield();
				
				// let some time pass before the shield is deactivated
				sleep(Params.DEBRIS_TIME);
				
				berth.deactivateShield();
				
			} catch (InterruptedException e) {
				 this.interrupt();
			}
		}
	}

}
