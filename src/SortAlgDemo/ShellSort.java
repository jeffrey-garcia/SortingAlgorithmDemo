package SortAlgDemo;


/**
 * The <code>ShellSort</code> class is a class implementing the 
 * SortAlgorithm which performs the sorting operation using shell  
 * sort algorithm.
 * 
 * <p>
 *
 * Copyright (c) 2005 UNISA. All Rights Reserved.
 *
 * <p>
 *
 * We grants you ("Licensee") a non-exclusive, royalty free, license to use, 
 * modify and redistribute this software in source and binary code form, 
 * provided that i) this copyright notice and license appear on all copies of 
 * the software; and ii) Licensee does not utilize the software in a manner 
 * which is disparaging to us. 
 *
 * <p>
 *
 * This software is provided "as is," without a warranty of any kind. all 
 * express or implied conditions, representations and warranties, including any 
 * implied warranty of merchantability, fitness for a particular purpose or 
 * non-infringement, are hereby excluded. we and its licensors shall not be 
 * liable for any damages suffered by licensee as a result of using, modifying 
 * or distributing the software or its derivatives. in no event shall we or its 
 * licensors be liable for any lost revenue, profit or data, or for direct, 
 * indirect, special, consequential, incidental or punitive damages, however 
 * caused and regardless of the theory of liability, arising out of the use of 
 * or inability to use software, even if we have been advised of the 
 * possibility of such damages.
 *
 * <p>
 *
 * @author Jeffrey Garcia
 * <p>
 *
 * @version 1.1 since Nov 2005
 * <p>
 *
 * @see	<a href="../api/InsertionSort.html">InsertionSort</a>
 * @see	<a href="../api/ShellSort.html">ShellSort</a>
 * @see	<a href="../api/MergeSort.html">MergeSort</a>
 * @see	<a href="../api/MergeSort2.html">MergeSort2</a>
 * @see	<a href="../api/QuickSort.html">QuickSort</a>
 */
public class ShellSort implements Runnable, SortAlgorithm {
	/**
	 * A <b>private</b> array of Comparable object, the data container which 
	 * store the data being sorted.
	 */
	private Comparable [] c;
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been completed, default value is false.
	 */
	private boolean finish = false;
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been paused, default value is false.
	 */
	private boolean pause = false;
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been terminated, default value is false.
	 */
	private boolean terminate = false;
	/**
	 * A <b>private</b> integer which holds the total number comparison occured 
	 * in the sorting
	 */
	private int comparisonCount = 0;
	/**
	 * A <b>private</b> integer which holds the total number of data swapping 
	 * occured in the sorting
	 */
	private int swapCount = 0;
	
	
	/**
	 * Create an instance of ShellSort.
	 */
	public ShellSort() {
		//
	}
	
	
	/**
	 * A <b>public</b> method to start the sorting operation.
	 */
	public void run() {
		sort();
	}
	
	
	/**
	 * A <b>public</b> method which set the data being sorted.
	 * @param c an array of Comparable Object which store the data being sorted. 
	 */
	public void setData(Comparable [] c) {
		this.c = c;
	}
	
	
	/**
	 * A <b>public</b> method which contain the sorting routine. 
	 */
	public void sort() {
		try {
			for (int gap=c.length/2; gap>0; gap=gap==2?1:(int)(gap/2.2)) {
				for (int i=gap; i<c.length; i++) {
					Comparable tmp = c[i];
					int j = i;
					
					comparisonCount ++;
					for (;j>=gap && tmp.compareTo(c[j-gap])<0; j-=gap) {
						//System.out.println("[Swap] " + c[j-gap] + " with " + c[j]); //Debugger
						
						c[j] = c[j-gap];
						
						swapCount ++;
						comparisonCount ++;
						
						// Pause after each sort
						pause = true;
						pause();
						
						if (terminate == true) {
							finish = true;
							break;
						}
					}
					
					//System.out.println("[Swap] " + tmp + " with " + c[j]); //Debugger
					
					c[j] = tmp;
					swapCount ++;
					
					// Pause after each sort
					pause = true;
					pause();
					
					if (terminate == true) {
						finish = true;
						break;
					}
				}
			}
			
			finish = true;
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	/**
	 * A <b>public</b> method to return the current data set.
	 * @return an array of Comparable Object which store the data being sorted.
	 */
	public Comparable [] getData() {
		return c;
	}
	
	
	/**
	 * A <b>public</b> method which pause the sorting process. 
	 * @param pause the current pause status
	 */
	public void pause(boolean pause) {
		this.pause = pause;
	}
	
	
	/**
	 * A <b>public</b> method which terminate the sorting process. 
	 */
	public void terminate() {
		terminate = true;
	}
	
	
	/**
	 * A <b>public</b> method which tells us if the sorting process 
	 * is being paused. 
	 * @return A boolean value, true if sorting process is paused, false if 
	 * otherwise.
	 */
	public boolean isPaused() {
		return pause;
	}
	
	
	/**
	 * A <b>public</b> method which tells us if the sorting process 
	 * is completed.
	 * @return A boolean value, true if sorting process is finished, false if 
	 * otherwise.
	 */
	public boolean isCompleted() {
		return finish;
	}
	
	
	/**
	 * A <b>public</b> method which gets the total number of data comparison 
	 * performed.
	 * @return An integer value containing the counter.
	 */
	public int getComparisonCount() {
		return comparisonCount;
	}
	
	
	/**
	 * A <b>public</b> method which gets the total number of data swapping 
	 * performed.
	 * @return An integer value containing the counter.
	 */
	public int getSwapCount() {
		return swapCount;
	}
	
	
	/**
	 * A <b>private</b> method which contain pause the sorting operation.
	 */
	private void pause() throws Exception {
		while (pause==true && terminate==false) {
			Thread.sleep(100); // re-check the status of pause every 100 milli-seconds
		}
	}
}