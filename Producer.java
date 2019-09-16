/**
 * Produces new cargo ships wanting to unload cargo at the space station.
 *
 * @author ngeard@unimelb.edu.au
 *
 * Zhijin Li 766041
 */

public class Producer extends Thread {

    // the wait zone at which ships will arrive
    private ArrivalWaitZone arrivalZone;

    // create a new producer
    Producer(ArrivalWaitZone arrivalZone) {
        this.arrivalZone = arrivalZone;
    }

    // cargo ships arrive at the arrival zone at random intervals.
    public void run() {
        while(!isInterrupted()) {
            try {
                // create a new cargo ship and send it to the arrival zone.
                Ship ship = Ship.getNewShip();
                arrivalZone.arrive(ship);

                // let some time pass before the next ship arrives
                sleep(Params.arrivalLapse());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
