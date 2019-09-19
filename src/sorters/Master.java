package sorters;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import scala.collection.mutable.ArraySeq;
import sorters.TcaMain.BubbleSortIn;
import sorters.TcaMain.InsertionSortIn;
import sorters.TcaMain.QuickSorttIn;
import sorters.TcaMain.SelectionSorttIn;

public class Master extends UntypedActor {
	  
	  private final int nrOfWorkers;
	 
	  private double pi;
	  private int nrOfResults;
	  private final long start = System.currentTimeMillis();
	 
	  private final ActorRef listener;
	  private final ActorRef workerBubbleSort;
	  private final ActorRef workerInsertionSort;
	  private final ActorRef workerQuickSort;
	  private final ActorRef workerSelectionSort;
	 
	  public Master(int nrOfWorkers, ActorRef listener) {
	    this.nrOfWorkers = nrOfWorkers;
	    this.listener = listener;
	 
	    workerBubbleSort = this.getContext().actorOf(Props.create(BubbleSort.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
	        "workerBubbleSort");
	    workerInsertionSort = this.getContext().actorOf(Props.create(InsertionSort.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
		        "workerInsertionSort");
	    workerQuickSort = this.getContext().actorOf(Props.create(QuickSort.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
		        "workerQuickSort");
	    workerSelectionSort = this.getContext().actorOf(Props.create(SelectionSort.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
		        "workerSelectionSort");
	  }
	  
	  public void onReceive(Object message) {
		  if (message instanceof BubbleSortIn) {
			  BubbleSortIn bubbleSortIn = (BubbleSortIn) message;
		    	workerBubbleSort.tell(new Work(bubbleSortIn.getNum()), getSelf());
		    
		  }
		  if (message instanceof InsertionSortIn) {
			  InsertionSortIn insertionSortIn = (InsertionSortIn) message;
			      workerInsertionSort.tell(new Work(insertionSortIn.getNum()), getSelf());
			    
			  }
		  if (message instanceof QuickSorttIn) {
			  QuickSorttIn quickSorttIn = (QuickSorttIn) message;
			      workerQuickSort.tell(new Work(quickSorttIn.getNum()), getSelf());
			    
			  }
		  if (message instanceof SelectionSorttIn) {
			  SelectionSorttIn selectionSorttIn = (SelectionSorttIn) message;
			      workerSelectionSort.tell(new Work(selectionSorttIn.getNum()), getSelf());
			    
			  }
		  else if (message instanceof Display) {
			  Display display = (Display) message;
		      // Send the result to the listener
		      
		      listener.tell(new Display(display.getMessage()), getSelf());
		      // Stops this actor and all its supervised children
		     // getContext().stop(getSelf());
		    }
		   else {
		    unhandled(message);
		  }
		}

}
