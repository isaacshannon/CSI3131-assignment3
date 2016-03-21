package assignment;

public class SimulationSlacker extends Simulation {
	
	final static int HOURS = 8;

	public static void main(String args[]) throws InterruptedException {
		int totalCatch = 0;
		int rods = 3;
		int bait = 3;
		System.out.println("\nStarting 5 run simulation...");
		System.out.println("Number of Rods: "+rods);
		System.out.println("Number of Bait: "+bait);
		for(int i=0;i<5;i++){
			totalCatch += runSimulation(rods,bait);
		}
		System.out.println("Avg. total fish caught: " +totalCatch/5);
		System.out.println("Avg. vacationer catch: " +totalCatch/50.0);
		
		totalCatch = 0;
		rods = 4;
		bait = 4;
		System.out.println("\nStarting 5 run simulation...");
		System.out.println("Number of Rods: "+rods);
		System.out.println("Number of Bait: "+bait);
		for(int i=0;i<5;i++){
			totalCatch += runSimulation(rods,bait);
		}
		System.out.println("Avg. total fish caught: " +totalCatch/5);
		System.out.println("Avg. vacationer catch: " +totalCatch/50.0);
		
		totalCatch = 0;
		rods = 3;
		bait = 4;
		System.out.println("\nStarting 5 run simulation...");
		System.out.println("Number of Rods: "+rods);
		System.out.println("Number of Bait: "+bait);
		for(int i=0;i<5;i++){
			totalCatch += runSimulation(rods,bait);
		}
		System.out.println("Avg. total fish caught: " +totalCatch/5);
		System.out.println("Avg. vacationer catch: " +totalCatch/50.0);
	}
	
	private static double runSimulation(int rods, int baits){
		final int NUM_VACATIONER = 10;
		int i;

		Vacation vacation = new Vacation(rods,baits,HOURS);

		Vacationer [] vacationers = new Vacationer[NUM_VACATIONER];
		int slackerNum = (int)(Math.random()*10);
		for (i=0; i< vacationers.length; i++){
			int rodWait = 1;
			if(i==slackerNum)
				rodWait = 2;
			else
				rodWait = 1;
			vacationers[i] = new Vacationer("Vacationer-"+i,vacation,rodWait);
		}

			/* Start the threads */
 		vacation.start();   // Start the vacation thread.
		for (Vacationer v:vacationers) v.start();  // Start vacationer threads
		
		try {vacation.join();} catch(InterruptedException e) { }; // Wait until vacation terminates.
		
		// Stop other threads.
		for (Vacationer v:vacationers) v.interrupt(); // Stop the vacationer threads.
		
		System.out.println("Finished run");
		
		return vacation.fishBucket;
	}
}
