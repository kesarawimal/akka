package sorters;

import akka.actor.UntypedActor;

public class BubbleSort extends UntypedActor {

	  public void onReceive(Object message) {
	    if (message instanceof Work) {
	    	long start = System.nanoTime();
	    	getSender().tell(new Display("Bubble sort start...."), getSelf());
	      Work work = (Work) message;
	      sort(work.getNum());
	      long duration = System.nanoTime() - start;
	      TcaMain.finishTime = duration * 1e-9;
	      getSender().tell(new Display("Bubble sort end...running time = "+duration * 1e-9 + " seconds"), getSelf());
	    } else {
	      unhandled(message);
	    }
	  }
	  
	  public void sort(int [] num) {
		    int j;
		    boolean flag = true;   // set flag to true to begin first pass
		    int temp;   //holding variable
		    
		    while ( flag ){
	           flag= false;    //set flag to false awaiting a possible swap
	           for (j=0;  j < num.length -1;  j++){
	              if ( num[j] < num[j+1] ){   // change to > for ascending sort
	                  temp = num[j];                //swap elements
	                  num[j] = num[j+1];
	                  num[j+1] = temp;
	                  flag = true;              //shows a swap occurred  
	              } 
	           }
	        }
		}
}
