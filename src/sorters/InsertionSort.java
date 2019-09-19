package sorters;

import akka.actor.UntypedActor;

public class InsertionSort extends UntypedActor {

	  public void onReceive(Object message) {
	    if (message instanceof Work) {
	    	long start = System.nanoTime();
	    	 getSender().tell(new Display("InsertionSort start...."), getSelf());
	      Work work = (Work) message;
	      sort(work.getNum());
	      long duration = System.nanoTime() - start;
	      TcaMain.finishTime = duration * 1e-9;
	      getSender().tell(new Display("InsertionSort end...running time = "+duration * 1e-9 + " seconds"), getSelf());
	    } else {
	      unhandled(message);
	    }
	  }

	public void sort(int [] num) {
	    int j;                     // the number of items sorted so far
	    int key;                // the item to be inserted
	    int i;  

	    for (j = 1; j < num.length; j++){    // Start with 1 (not 0)
	        key = num[j];
	        for (i = j - 1; (i >= 0) && (num[i] < key); i--){  // Smaller values are moving up
                num[i+1] = num[i];
	        }
	        num[i+1] = key;    // Put the key in its proper location
	    }
	    
	}
}
