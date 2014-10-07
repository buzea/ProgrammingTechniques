package Queues;

import java.util.Random;

public class CustomerGenerator extends Thread {
	private int minArrival, maxArrival, minServiceTime, maxServiceTime,currentTime,endTime;
	private Random rand;
	private int temp;
	/**
	 * Result of the Client generator. Can be publicly accessed
	 */
	public Client newClient;
	
	/**
	 * Builds a Client generator that respects the transmitted parameters
	 * @param minArrival - minimum time between arrival of two customers
	 * @param maxArrival - maximum time between arrival of two customers
	 * @param minServiceTime - the minimum amount of time that a customer may require for processing
	 * @param maxServiceTime - the maximum amount of time that a customer may require for processing
	 * @param simulationStartTime - beginning of the simulation/customer generation
	 * @param endTime - ending time for the simulation/customer generation
	 */
	public CustomerGenerator(int minArrival,int maxArrival,int minServiceTime,int maxServiceTime,int simulationStartTime,int endTime)
	{
		this.minArrival=minArrival;
		this.maxArrival=maxArrival;
		this.minServiceTime=minServiceTime;
		this.maxServiceTime=maxServiceTime;
		rand=new Random();
		this.currentTime=simulationStartTime;
		this.endTime=endTime;
		newClient=null;
	}
	/**
	 * Randomly generates customers in the newClient field.
	 */
	public void run()
	{
		int clientNb=1;
		while(currentTime<=(endTime-maxServiceTime)){
		temp=rand.nextInt((maxServiceTime-minServiceTime)) + minServiceTime;//generate random service time
		newClient=new Client(currentTime,temp,clientNb);//generate new client
		clientNb++;
		temp=rand.nextInt((maxArrival-minArrival)) + minArrival; // generate time of arrival for next customer
		currentTime+=temp; //update current time for next customer
		try {
			sleep(temp);
		} catch (InterruptedException e) {}
		}
		
	}
	
	
}
