package sorters;

import akka.actor.UntypedActor;

public class QuickSort extends UntypedActor {
	int[] num;
	
	public void onReceive(Object message) {
	    if (message instanceof Work) {
	    	long start = System.nanoTime();
	    	getSender().tell(new Display("quicksort start ...."), getSelf());
	      Work work = (Work) message;
	      sort(work.getNum());
	      long duration = System.nanoTime() - start;
	      TcaMain.finishTime = duration * 1e-9;
	      getSender().tell(new Display("quicksort end...running time = "+duration * 1e-9 + " seconds"), getSelf());
	    } else {
	      unhandled(message);
	    }
	  }

	public void sort(int[] num) {
		this.num = num;
        quicksort(0, num.length - 1);
	}

	public void quicksort(int low, int high) {
	    int i = low, j = high;
	    // Get the pivot element from the middle of the list
	    int pivot = num[low + (high-low)/2];

	    // Divide into two lists
	    while (i <= j) {
	        // If the current value from the left list is smaller then the pivot
	        // element then get the next element from the left list
	        while (num[i] < pivot) {
	            i++;
	        }
	        // If the current value from the right list is larger then the pivot
	        // element then get the next element from the right list
	        while (num[j] > pivot) {
	           j--;
	        }

	        // If we have found a values in the left list which is larger then
	        // the pivot element and if we have found a value in the right list
	        // which is smaller then the pivot element then we exchange the
	        // values.
	        // As we are done we can increase i and j
	        if (i <= j) {
	            exchange(i, j);
	            i++;
	            j--;
	        }
	    }
	    // Recursion
	    if (low < j)
	        quicksort(low, j);
	    if (i < high)
	        quicksort(i, high);
	}
	
    private void exchange(int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
