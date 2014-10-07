package Queues;

/**
 * @author Vlad
 *
 */
public class Client {
	//fields related to clients
	private int arrivalTime,serviceTime,finishTime; //all these values express minutes
	private int ID=0;
	/**
	 * Creates a new customer with specified values
	 * @param arrivalTime the time when the client shows up for service
	 * @param serviceTime the time he needs to be served
	 * 
	 */
	public Client(int arrivalTime,int serviceTime,int id)
	{
		this.arrivalTime=arrivalTime;
		this.serviceTime=serviceTime;
		finishTime=-1;	// we set an initial value just to know the field is allocated correctly
						// real value will be set when entering a queue
		ID=id;
	}
	
	
	
	public int getID(){
		return ID;
	}
	/**
	 * Getter method for service time
	 * @return serviceTime
	 */
	public int getServiceTime(){
		return serviceTime;
	}
	
	/**
	 * Getter method for arrival time
	 * @return arrivalTime
	 */
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	/**
	 * Getter method for finish time
	 * @return finishTime
	 */
	public int getFinishTime(){
		return finishTime;
	}
	
	/**
	 * Setter method for finishTime
	 * @param value new finishTime
	 */
	public void setFinishTime(int value){
		finishTime=value;
	}
	/**
	 * Copies the caller client 
	 * @return new Client with same data as caller 
	 */
	public Client copy()
	{
		Client temp=new Client(arrivalTime,serviceTime,ID);
		temp.setFinishTime(finishTime);
		return temp;
	}
	
	/**
	 * Returns the arrival time, service time and finish time of the client in string form
	 * @return String containing :arrivalTime serviceTime finishTime
	 */
	public String toString(){
		return ""+arrivalTime+" "+serviceTime+" "+finishTime;
	}
	
	public boolean equals(Object c){
		Client t=(Client)c;
		if(this.getID()==t.getID())
			return true;
		return false;
	}

}
