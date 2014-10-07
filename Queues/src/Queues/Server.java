package Queues;


import java.util.Iterator;
import java.util.LinkedList;

public class Server extends Thread {
	
	private LinkedList<Client> que;		// supports the clients in order of appearance
	private int maxTime,nbClients,idle;
	private int start=0,end,number;		//start time and end time
	private static long i=0;			// counts the number of clients processed 
	private static long totalProcessingTime=0,totalWaitingTime=0;// total time spent by customers for service and waiting
	private boolean open;				//active or inactive Server
	private String log;					//cumulates Log info for display
	public static final int intervalStart=120, intervalEnd=180; // specific interval start and end time
	private static int intervalClients=0,intervalService=0,intervalWait=0,intervalIdle=0;
	/**
	 * Creates a new Server that processes a queue
	 *@param start is the start time of the thread
	 *@param end is the end time of the thread
	 *@param ID is the Server number
	 */
	public Server(int start,int end,int ID){
		que=new LinkedList<Client>();
		setMaxTime(0);
		setNbClients(0);
		idle=0;
		this.start=start;
		this.end=end;
		number=ID;
		open=false;
		setLog(new String());
		
		
	}
	
	/** Getter method for the queue maximum waiting time.
	 * 	Also equal to the time  the last customer has to wait.
	 * @return the maxTime
	 */
	public int getMaxTime() {
		return maxTime;
	}

	/**	Setter method for the queue maximum waiting time
	 * @param maxTime the maxTime to set
	 */
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	
	/**
	 * Sets open attribute to true
	 */
	public void openServer()
	{
		open=true;
	}
	/**
	 * Sets the open attribute to false
	 */
	public void closeServer(){
		open=false;
	}
	
	/**
	 * Checks the open attribute of a server
	 * @return true if open, false if closed
	 */
	public boolean isOpen()
	{
		return open;
	}

	/**
	 * Adds a client to the queue
	 * @param c the Client to be added to the queue
	 */
	public synchronized void addClient(Client c){
		Client temp=c.copy();
		que.addLast(temp);
		log+= (toTime(start)+": Server "+number+ " : client "+temp.getID()+" joined queue \r\n");
		this.setMaxTime(maxTime + temp.getServiceTime());
		setNbClients(getNbClients() + 1);
	}
	/**
	 * Returns the time amount (in minutes) that the server has been idle (closed and not processing)
	 * @return idle time
	 */
	public synchronized int getIdleTime(){
		return idle;
	}
	
	/**
	 * Checks if the server has been idle for more than the transmitted time
	 * @param time - threshold time
	 * @return true if server has been idle for more than the parameter, false otherwise
	 */
	public synchronized boolean isIdle(int time)
	{
		if(idle>time)
			return true;
		return false;
	}
	/**
	 * Polls the last client from the queue
	 * @return the last client in the queue
	 */
	public Client extractLastClient(){
		Client temp=que.pollLast();
		if(temp!=null){
		nbClients--;
		maxTime-=temp.getServiceTime();
		return temp;
		}
		else
			return null;
	}
	/**
	 * Checks if transmitted Client is the first in the queue
	 * @param c - Client to check
	 * @return true if first in queue, false otherwise
	 */
	public boolean isFirst(Client c){
		if(que.peekFirst().equals(c))
			return true;
		return false;
	}

	
	/**
	 * Moves last Clients from transmitted Server to caller Server
	 * @param number = number of clients to be moved
	 * @param s Server with too many clients
	 */
	public synchronized void moveClients(int number,Server s)
	{
		int i;
		Client temp;
		for(i=1;i<=number;i++){
			temp=s.extractLastClient();
			que.addFirst(temp);
			maxTime+=temp.getServiceTime();
			nbClients++;
		}
	}
	/**
	 * Processes clients if there are any in there are any in the queue corresponding to the server
	 */
	public void run()
	{
		int processingTime=1;
		Client temp=que.pollFirst();
		
		while(start<=end){
			if(temp!=null)
			{
			setNbClients(getNbClients() - 1);
			processingTime=temp.getServiceTime();i++;totalProcessingTime+=processingTime;
			temp.setFinishTime(start+processingTime);totalWaitingTime-=(temp.getArrivalTime()-temp.getFinishTime());
			if((start>=intervalStart)&&(start<=intervalEnd))
				{intervalClients++;
				intervalService+=temp.getServiceTime();
				intervalWait-=(temp.getArrivalTime()-temp.getFinishTime());
				}
			//System.out.println(start+": Server "+number+ " Client "+i+" - processing. Data:" + temp);
			setLog(getLog() + (toTime(start)+": Server "+number+ " processing client "+temp.getID()+" (client arrived) \r\n"));
			this.setMaxTime(maxTime-processingTime);
			
			}
			else
			{
				idle++;
				processingTime=1;
				if((start>=intervalStart)&&(start<=intervalEnd))
					intervalIdle++;
			}
				
			
			
			
			try {
				sleep(processingTime); // processing client or take step
			} catch (InterruptedException e) {}
			
			start+=processingTime; //update time
			if(temp!=null)
				setLog(getLog() + (toTime(start)+": Server "+number+ " : client "+temp.getID()+" finished processing  \r\n")); // client leaves the queue
			temp=que.pollFirst();// take next customer
			
		}
		 
	}
	
	

	@SuppressWarnings("unused")
	private void showQue(){
		Iterator<Client> i = que.iterator();
		Client c;
		while(i.hasNext())
		{
			c=i.next();
			System.out.print(c.toString()+" ");
			
		}
	
	}

	/**
	 * Getter for the number of clients in a queue
	 * @return the nbClients
	 */
	public int getNbClients() {
		return nbClients;
	}

	/**
	 * Setter for the number of clients in a queue
	 * @param nbClients the nbClients to set
	 */
	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}

	/**Setter for the log
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**Getter for the log
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}
	@SuppressWarnings("unused")
	public static String toTime(int minutes){
		int day,hour,min;
		String o="";
		min=minutes%60;
		minutes/=60;
		hour=minutes % 24;
		day=minutes/24;
		if(hour>9)
			o+=""+hour;
		else
			o+="0"+hour;
		if(min>9)
			o+=":"+min;
		else
			o+=":0"+min;
		return o;
		
	}
	/**
	 * Computes the average waiting time for the whole simulation
	 * @return average waiting time
	 */
	public static long getAverageWaitingTime(){
		if(i>0)
			return (totalWaitingTime/i);
		return 0;
	}
	/**
	 * Computes the average service time for the whole simulation
	 * @return average service time
	 */
	public static long getAverageServiceTime(){
		if(i>0)
			return (totalProcessingTime/i);
		return 0;
	}
	/**
	 * Creates the message regarding the requested information for the specified interval
	 * @return string with average service time, average waiting time and total idle time
	 */
	public static String intervalData()
	{
		String s=new String();
		int temp;
		try{temp=intervalService/intervalClients;
		s+= ("Average service time in predefined interval: "+temp+"\r\n");
		temp=intervalWait/intervalClients;
		s+= ("Average waiting time in predefined interval: "+temp+"\r\n");
		s+=("Total idle time in predefined interval:"+intervalIdle+"\r\n");
		}catch(Exception e2){
			s="Sampling iterval is not contained in the simulation interval!";
		}
		return s;
	}
	

}
