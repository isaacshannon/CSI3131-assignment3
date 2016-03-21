package assignment;

import java.util.concurrent.Semaphore;

class Vacationer extends Thread { // Class for the auto threads.

	final static int MINUTE = 50; // ms per minute in simulation
	private String threadName;
	private Vacation vacation;

	public Vacationer(String threadName,Vacation vacation) {
		this.threadName = threadName;
		this.vacation = vacation;
	}

	public void run() {
		Semaphore rodSemaphore = vacation.rodSemaphore;
		Semaphore baitSemaphore = vacation.baitSemaphore;

		while (true) {
			//Acquire rod
			try {
				rodSemaphore.acquire();
			} 
			catch (InterruptedException e)
			{
				break;
			}

			//Acquire bait
			try {
				baitSemaphore.acquire();
			} 
			catch (InterruptedException e)
			{
				break;
			}
			
			// Go Fishing
			try {
				sleep(20*MINUTE);
			} catch (Exception e) {
				break;
			}
			
			//Release Bait
			baitSemaphore.release();
			
			//Count fish caught
			int fish = (int)(Math.random()*10.99);
			vacation.fishBucket+=fish;
			System.out.println(threadName+" caught "+fish+" fish");
			
			//Wait to release rod
			try {
				sleep(1*MINUTE);
			} catch (Exception e) {
				break;
			}
			
			//Release the rod
			rodSemaphore.release();

			// Terminate
			if (isInterrupted())
				break;
		}
		System.out.println(threadName + " terminated");
	}

}
