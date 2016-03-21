package assignment;

import java.util.concurrent.Semaphore;

class Vacation extends Thread // The ferry Class
{
	final static int MINUTE = 50; // ms per minute in simulation
	public int fishBucket = 0; //total fish caught by users
	int minutes; //total number of minutes to run simulation

	// Semaphores
	Semaphore rodSemaphore;
	Semaphore baitSemaphore;

	public Vacation(int rods, int baits,int hours) {
		this.minutes = hours*60;
		rodSemaphore = new Semaphore(rods, true);
		baitSemaphore = new Semaphore(baits, true);
	}

	public void run() {
		int i;

		for (i = 0; i < minutes; i++) {
			try {
				sleep(MINUTE);
			} catch (Exception e) {
			}

		}
	}
}
