package assignment;

public class SimulationBasic extends Simulation {
	
	final static int HOURS = 1;

	public static void main(String args[]) throws InterruptedException {
		final int NUM_VACATIONER = 10;
		int i;

		Vacation vacation = new Vacation(3,3,HOURS);

		Vacationer [] vacationers = new Vacationer[NUM_VACATIONER];
		for (i=0; i< vacationers.length; i++) vacationers[i] = new Vacationer("Vacationer-"+i,vacation);

			/* Start the threads */
 		vacation.start();   // Start the vacation thread.
		for (Vacationer v:vacationers) v.start();  // Start vacationer threads
		
		try {vacation.join();} catch(InterruptedException e) { }; // Wait until vacation terminates.
		System.out.println("Vacation stopped.");
		// Stop other threads.
		for (Vacationer v:vacationers) v.interrupt(); // Stop the vacationer threads.
	}
}
