package sorters;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class TcaMain {

	public static double finishTime;
	public static void main(String[] args) {
		int[] data1 = new int[50000];
		for (int i=0; i < data1.length; i++){
			data1[i] = (int) (Math.random() * 65536);
		}
		int[] data2 = data1.clone();
		int[] data3 = data1.clone();
		int[] data4 = data1.clone();
		
		long start = System.nanoTime();
		// Create an Akka system
	    ActorSystem system = ActorSystem.create("sortSystem");
	 
	    // create the result listener, which will print the result and shutdown the system
	    final ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
	 
	    // create the master
	    ActorRef master = system.actorOf(Props.create(Master.class, 50, listener), "master");
	 
	    // start the calculation
	    master.tell(new BubbleSortIn(data1), ActorRef.noSender());
	    master.tell(new InsertionSortIn(data2), ActorRef.noSender());
	    master.tell(new QuickSorttIn(data3), ActorRef.noSender());
	    master.tell(new SelectionSorttIn(data4), ActorRef.noSender());
		long duration = System.nanoTime() - start;
		Display.show("running time = " + duration * 1e-9 + " seconds");
	}
	
	static class BubbleSortIn{
		private int [] num;
		
		public BubbleSortIn(int [] num) {
			this.num = num;
		}
		
		public int[] getNum() {
			return num;
		}
	}
	static class InsertionSortIn{
		private int [] num;
		
		public InsertionSortIn(int [] num) {
			this.num = num;
		}
		
		public int[] getNum() {
			return num;
		}
	}
	static class QuickSorttIn{
		private int [] num;
		
		public QuickSorttIn(int [] num) {
			this.num = num;
		}
		
		public int[] getNum() {
			return num;
		}
	}
	static class SelectionSorttIn{
		private int [] num;
		
		public SelectionSorttIn(int [] num) {
			this.num = num;
		}
		
		public int[] getNum() {
			return num;
		}
	}
	
	public static class Listener extends UntypedActor {
	    public void onReceive(Object message) {
	      if (message instanceof Display) {
	        Display display = (Display) message;
	        System.out.println(display.getMessage());
	      } else {
	        unhandled(message);
	      }
	    }
	  }
}
