package SortAlgDemo;


/**
 * The <code>SortAlgorithm</code> class is an interface.
 * <p>
 * The SortAlgorithm interface should be implemented by any class whose 
 * instances are intended to perform the low-level sorting operation. 
 * The class must define the following methods:
 * <ul>
 *	<li>public void setData(Comparable [] c)</li>
 *	<li>public void sort()</li>
 *	<li>public Comparable [] getData()</li>
 *	<li>public void pause(boolean pause)</li>
 *	<li>public void terminate()</li>
 *	<li>public boolean isPaused()</li>
 *	<li>public boolean isCompleted()</li>
 *	<li>public int getComparisonCount()</li>
 *	<li>public int getSwapCount()</li>
 * </ul>
 * <p>
 * This interface is designed to provide a common template for objects 
 * that execute sorting routines with pre-defined sort algorithm. Such 
 * that the importation of new sorting algorithms to the SortVisualizer 
 * software could be more easy by means of reducing the program maintenance 
 * effort.
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
public interface SortAlgorithm {
	/**
	 * A <b>public</b> method which set the data being sorted.
	 * @param c an array of Comparable Object which store the data being sorted. 
	 */
	public void setData(Comparable [] c);
	
	/**
	 * A <b>public</b> method which contain the sorting routine. 
	 */
	public void sort();
	
	/**
	 * A <b>public</b> method to return the current data set.
	 * @return an array of Comparable Object which store the data being sorted.
	 */
	public Comparable [] getData();
	
	/**
	 * A <b>public</b> method which pause the sorting process. 
	 * @param pause the current pause status
	 */
	public void pause(boolean pause);
	
	/**
	 * A <b>public</b> method which terminate the sorting process. 
	 */
	public void terminate();
	
	/**
	 * A <b>public</b> method which tells us if the sorting process 
	 * is being paused. 
	 * @return A boolean value, true if sorting process is paused, false if 
	 * otherwise.
	 */
	public boolean isPaused();
	
	/**
	 * A <b>public</b> method which tells us if the sorting process 
	 * is completed.
	 * @return A boolean value, true if sorting process is finished, false if 
	 * otherwise.
	 */
	public boolean isCompleted();
	
	/**
	 * A <b>public</b> method which gets the total number of data comparison 
	 * performed.
	 * @return An integer value containing the counter.
	 */
	public int getComparisonCount();
	
	/**
	 * A <b>public</b> method which gets the total number of data swapping 
	 * performed.
	 * @return An integer value containing the counter.
	 */
	public int getSwapCount();
}