package assignment;

public class SimulationBasic extends Simulation {
	
	final static int HOURS = 8;

	public static void main(String args[]) throws InterruptedException {
		runSimulation(3,3);
		runSimulation(4,4);
		runSimulation(3,4);
	}
	
	private static void runSimulation(int rods, int baits){
		final int NUM_VACATIONER = 10;
		int i;
		
		System.out.println("\nStarting basic simulation...");
		System.out.println("Number of Rods: "+rods);
		System.out.println("Number of Bait: "+baits);

		Vacation vacation = new Vacation(rods,baits,HOURS);

		Vacationer [] vacationers = new Vacationer[NUM_VACATIONER];
		for (i=0; i< vacationers.length; i++) vacationers[i] = new Vacationer("Vacationer-"+i,vacation,1);

			/* Start the threads */
 		vacation.start();   // Start the vacation thread.
		for (Vacationer v:vacationers) v.start();  // Start vacationer threads
		
		
		try {vacation.join();} catch(InterruptedException e) { }; // Wait until vacation terminates.
		System.out.println("Fish caught: "+vacation.fishBucket);
		System.out.println("Average Vacationer catch: "+vacation.fishBucket/10.0);
		System.out.println("Vacation stopped.");
		
		// Stop other threads.
		for (Vacationer v:vacationers) v.interrupt(); // Stop the vacationer threads.
	}
}
