package sorters;

import akka.actor.UntypedActor;

public class SelectionSort extends UntypedActor {

	  public void onReceive(Object message) {
	    if (message instanceof Work) {
	    	long start = System.nanoTime();
	    	 getSender().tell(new Display("SelectionSort start...."), getSelf());
	      Work work = (Work) message;
	      sort(work.getNum());
	      long duration = System.nanoTime() - start;
	      TcaMain.finishTime = duration * 1e-9;
	      getSender().tell(new Display("SelectionSort end...running time = "+duration * 1e-9 + " seconds"), getSelf());
	    } else {
	      unhandled(message);
	    }
	  }

	public void sort(int [] num) {
        int i, j, first, temp;
        
	    for ( i = num.length - 1; i > 0; i--){
            first = 0;   //initialise to subscript of first element
	        for (j = 1; j <= i; j ++){   //locate smallest element between positions 1 and i.
               if( num[ j ] < num[ first ] )         
                   first = j;
            }
            temp = num[ first ];   //swap smallest found with element in position i.
            num[ first ] = num[ i ];
            num[ i ] = temp; 
        }           
	    
	}
}
