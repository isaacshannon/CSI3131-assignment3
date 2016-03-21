package assignment;

import java.util.concurrent.Semaphore;

class Vacation extends Thread // The ferry Class
{
	final static int MINUTE = 50; // ms per minute in simulation
	int minutes;
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
		System.out.println("Starting vacation simulation");

		
		for (i = 0; i < minutes; i++) {
			try {
				sleep(MINUTE);
			} catch (Exception e) {
			}
		}
	}
}
