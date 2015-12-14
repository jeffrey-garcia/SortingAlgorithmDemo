package SortAlgDemo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.applet.*;

import java.io.*;
import java.util.*;


/**
 * The <code>SortHandler</code> class is a subclass of the Thread class. 
 * Hence the handler itself inherits all the behavior and methods of a generic 
 * Thread Object, as a result to implement the "multi-threading" feature for 
 * the software itself. Note that this class should possess some public access 
 * methods to allow external call (from the SortTimer) such that the SortTimer 
 * could access its current state.
 * <br>
 * The handler would create an instance of SortAlgorithm Object, in which the 
 * type of sorting algorithm to be instantiated depends on user's selection 
 * from the GUI (SortVisualizer).
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
 * @see	<a href="../api/SortTimer.html">SortTimer</a>
 * @see	<a href="../api/SortHandler.html">SortHandler</a>
 * @see	<a href="../api/BarChartPanel.html">BarChartPanel</a>
 * @see	<a href="../api/SortVisualizer_UIColor.html">SortVisualizer_UIColor</a>
 */
public class SortHandler extends Thread {
	/**
	 * A <b>private</b> SortVisualizer object as a referenced for interaction.
	 */
	private SortVisualizer sv;
	
	/**
	 * A <b>private</b> Vector object which holds the initial data in ascending 
	 * order.
	 */
	private Vector vDataAsce;
	/**
	 * A <b>private</b> Vector object which holds the initial data in decending 
	 * order.
	 */
	private Vector vDataDesc;
	/**
	 * A <b>private</b> Vector object which holds the initial data in random 
	 * order.
	 */
	private Vector vDataRand;
	/**
	 * A <b>private</b> Vector object which holds the initial data in nearly 
	 * sorted order.
	 */
	private Vector vDataNearlySort;
	
	/**
	 * A <b>private</b> object of type SortAlgorithm, it actual type will be 
	 * initialized at runtime, therefore benefits the advantages of 
	 * polymporphism.
	 */
	private SortAlgorithm alg;
	
	/**
	 * A <b>private</b> integer which indicate the selected sorting 
	 * algorithm.
	 * <br>
	 * 0 - Insertion Sort
	 * <br>
	 * 1 - Shell Sort
	 * <br>
	 * 2 - Quick Sort
	 * <br>
	 * 3 - Merge Sort
	 */
	private int sortingAlg = 0;
	
	/**
	 * A <b>private</b> integer which indicate the selected number of data 
	 * being sorted.
	 */		
	private int sortingDataNo = 0;
	
	/**
	 * A <b>private</b> integer which indicate the selected initial ordering 
	 * of data being sorted.
	 * <br>
	 * 0 - Increasing Order
	 * <br>
	 * 1 - Decreasing Order 
	 * <br>
	 * 2 - Random Order 
	 * <br>
	 * 3 - Nearly Sorted
	 */
	private int sortingOrder = 0;
	
	/**
	 * A <b>private</b> integer which indicate the selected sorting speed.
	 * <br>
	 * 0 - Step
	 * <br>
	 * 1 - Slow
	 * <br>
	 * 2 - Medium
	 * <br>
	 * 3 - Fast
	 */
	private int sortingSpeed = 0;
	
	/**
	 * A <b>private</b> array of Comparable object, the data container which 
	 * store the data being sorted.
	 */
	private Comparable [] c;
	
	/**
	 * A <b>private</b> integer which holds the waiting time, this value is 
	 * dependent to the selected sorting speed.
	 */
	private int waitingTime;
	
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been completed, default value is false.
	 */
	private boolean finish = false;
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been terminated, default value is false.
	 */
	private boolean terminate = false;
	
	
	public static void main(String [] args) {
		new SortHandler(3,500,2,3).start();
	}
	
	
	public SortHandler(int sortingAlg, int sortingDataNo, int sortingOrder, int sortingSpeed) {
		this.sortingAlg = sortingAlg;
		this.sortingDataNo = sortingDataNo;
		this.sortingOrder = sortingOrder;
		this.sortingSpeed = sortingSpeed;
		
		initData();
		initSort();
	}
	
	
	/**
	 * Create an instance of SortHandler.
	 * <p>
	 * @param sv A SortVisualizer object.
	 * @param sortingAlg the selected sorting algorithm.
	 * @param sortingDataNo the selected data size.
	 * @param sortingOrder the selected initial ordering of data
	 * @param sortingSpeed the selected sorting speed
	 */
	public SortHandler(SortVisualizer sv, int sortingAlg, int sortingDataNo, int sortingOrder, int sortingSpeed) {
		this.sv = sv;
		this.sortingAlg = sortingAlg;
		this.sortingDataNo = sortingDataNo;
		this.sortingOrder = sortingOrder;
		this.sortingSpeed = sortingSpeed;
		
		initData();
		initSort();
	}
	
	
	/**
	 * A <b>private</b> method to initialize the values of data being sorted.
	 */
	private void initData() {
		//Preset the initial data values (real-numbers) available for sorting
		vDataAsce = new Vector();
		for (int i=0; i<20; i++) {
			vDataAsce.addElement(new Float ((float) (0.05 * (i+1))));
		}
		
		vDataDesc = new Vector();
		for (int i=0; i<20; i++) {
			vDataDesc.addElement(new Float ((float) (0.05 * 20 - 0.05 * i)));
		}
		
		vDataRand = new Vector();
		vDataRand.addElement(new Float((float) 0.60));
		vDataRand.addElement(new Float((float) 0.75));
		vDataRand.addElement(new Float((float) 0.45));
		vDataRand.addElement(new Float((float) 0.90));
		vDataRand.addElement(new Float((float) 0.30));
		vDataRand.addElement(new Float((float) 0.55));
		vDataRand.addElement(new Float((float) 0.35));
		vDataRand.addElement(new Float((float) 0.20));
		vDataRand.addElement(new Float((float) 0.80));
		vDataRand.addElement(new Float((float) 0.10));
		vDataRand.addElement(new Float((float) 1.00));
		vDataRand.addElement(new Float((float) 0.65));
		vDataRand.addElement(new Float((float) 0.05));
		vDataRand.addElement(new Float((float) 0.40));
		vDataRand.addElement(new Float((float) 0.15));
		vDataRand.addElement(new Float((float) 0.95));
		vDataRand.addElement(new Float((float) 0.25));
		vDataRand.addElement(new Float((float) 0.70));
		vDataRand.addElement(new Float((float) 0.85));
		vDataRand.addElement(new Float((float) 0.50));
		
		vDataNearlySort = new Vector();
		vDataNearlySort.addElement(new Float((float) 0.05));
		vDataNearlySort.addElement(new Float((float) 0.10));
		vDataNearlySort.addElement(new Float((float) 0.15));
		vDataNearlySort.addElement(new Float((float) 0.25));
		vDataNearlySort.addElement(new Float((float) 0.20));
		vDataNearlySort.addElement(new Float((float) 0.30));
		vDataNearlySort.addElement(new Float((float) 0.35));
		vDataNearlySort.addElement(new Float((float) 0.45));
		vDataNearlySort.addElement(new Float((float) 0.40));
		vDataNearlySort.addElement(new Float((float) 0.60));
		vDataNearlySort.addElement(new Float((float) 0.55));
		vDataNearlySort.addElement(new Float((float) 0.60));
		vDataNearlySort.addElement(new Float((float) 0.55));
		vDataNearlySort.addElement(new Float((float) 0.70));
		vDataNearlySort.addElement(new Float((float) 0.75));
		vDataNearlySort.addElement(new Float((float) 0.90));
		vDataNearlySort.addElement(new Float((float) 0.85));
		vDataNearlySort.addElement(new Float((float) 0.95));
		vDataNearlySort.addElement(new Float((float) 0.85));
		vDataNearlySort.addElement(new Float((float) 1.00));
	}
	
	
	/**
	 * A <b>private</b> method to initialize the sorting operation.
	 * <p>
	 * Tasks included:
	 * <ul>
	 *  <li>initialising the data container c and putting data values 
	 * 		into it,</li>
	 *  <li>instantiate the sortingAlg object depending on user's selection.</li>
	 * </ul>
	 */
	private void initSort() {
		//Set the sorting algorithm
		if (sortingAlg == 0) {
			alg = new InsertionSort();
		} else if (sortingAlg == 1) {
			alg = new ShellSort();
		} else if (sortingAlg == 2) {
			alg = new QuickSort();
		} else if (sortingAlg == 3) {
			alg = new MergeSort();
		} else if (sortingAlg == 4) {
			alg = new MergeSort2();
		}
		
		//Set the no. of data to sort
		c = new Comparable [sortingDataNo];
		
		//Set the initial sorting order
		if (sortingDataNo <= 20) {
			for (int i=0; i<sortingDataNo; i++) {
				if (sortingOrder == 0) {
					c [i] = (Float)vDataAsce.elementAt(i);
				} else if (sortingOrder == 1) {
					c [i] = (Float)vDataDesc.elementAt(i);
				} else if (sortingOrder == 2) {
					c [i] = (Float)vDataRand.elementAt(i);
				} else if (sortingOrder == 3) {
					c [i] = (Float)vDataNearlySort.elementAt(i);
				}
			}
		} else {
			c = new RandomData().getData(sortingDataNo);
		}
		
		//Set the waiting time for each sort
		if (sortingSpeed == 0) {
			waitingTime = 0; //indicate the move is by step
		} else if (sortingSpeed == 1) {
			waitingTime = 5000; //5 Sec
		} else if (sortingSpeed == 2) {
			waitingTime = 2500; //2.5 Sec
		} else if (sortingSpeed == 3) {
			waitingTime = 1000; //1 Sec
		} else if (sortingSpeed == 4) {
			waitingTime = 1; //1 Milli-Sec, almost non-stop
		}
		
		//Customized data for debugging test
		/*
		c [0] = new Float((float)0.81);
		c [1] = new Float((float)0.94);
		c [2] = new Float((float)0.11);
		c [3] = new Float((float)0.96);
		c [4] = new Float((float)0.12);
		c [5] = new Float((float)0.35);
		c [6] = new Float((float)0.17);
		c [7] = new Float((float)0.95);
		c [8] = new Float((float)0.28);
		c [9] = new Float((float)0.58);
		c [10] = new Float((float)0.41);
		c [11] = new Float((float)0.75);
		c [12] = new Float((float)0.15);
		*/
		
		//Set the data
		alg.setData(c);
	}
	
	
	/**
	 * A <b>public</b> run method being implemented for multi-threading.
	 * <p>
	 * When invoked, it also calls the start of sorting operation. And every 
	 * 10 milli-seconds it will check if there has been any terminate signal 
	 * being sent by user. If yes, it will stop the sorting operation via 
	 * Thread interruption.
	 * <p>
	 * During the sorting, it will continously collect the current data 
	 * container and pass it to drawChart() method of the SortVisualizer, 
	 * such that the bar charts representing the sorting status can be drawn.
	 */
	public void run() {
		int stepCount = 0; //Just for reference when debugging, not accurate
		
		try {
			//Print the initial data
			System.out.println("Initial Data:");
			for (int i=0; i<c.length; i++) {
				System.out.print(c [i] + " # ");
			}
			System.out.println();
			
			//Put the Sorting Object into a Thread process and invoke it
			Thread t = new Thread((Runnable)alg,"SortingThread");
			t.start();
			
			while (alg.isCompleted()==false) {
				if (alg.isPaused()) {
					if (waitingTime == 0) {
						System.out.println("waiting...");
						while (true) {
							try {
								sleep(1000); //wait until the thread is interrupted
							} catch (InterruptedException ie) {
								if (terminate == false) {
									System.out.println("* Proceed next sorting *"); //Debugger
								} else {
									System.out.println("* Sorting terminated *"); //Debugger
								}
								
								break;
							}
						}
						
					} else {
						try {
							sleep(waitingTime); //wait for the time specified, then re-check
						} catch (InterruptedException ie) {
							if (terminate == false) {
								System.out.println("* Sorting is paused *"); //Debugger
								while (true) {
									try {
										sleep(1000);
									} catch (InterruptedException ie2) {
										System.out.println("* Sorting un-paused *"); //Debugger
										break;
									}
								}
							} else {
								System.out.println("* Sorting is terminated *"); //Debugger
							}
						}
					}
					
					alg.pause(false);
					stepCount ++;
					
					Comparable [] sorted_c = alg.getData();
					
					/**********************************************
					 * Debugger
					 *
					 * Since this part will produce large amount 
					 * of output values to the Applet Console, 
					 * and causing exhautive CPU consumption 
					 * when the number of lines of text reach 
					 * a certain extend
					 **********************************************/
					System.out.println("Result of Sort Step (" + stepCount + ")");
					for (int i=0; i<sorted_c.length; i++) {
						System.out.print((Float)sorted_c[i] + " # ");
					}
					System.out.println();
					
					//Print the data to GUI Chart
					if (sv != null) {
						sv.drawChart(sorted_c, alg.getComparisonCount(), alg.getSwapCount());
					}
					
				} else {
					sleep(10); //wait for 10 milli-seconds, then re-check
				}
			}
			
			if (terminate == false) {
				System.out.println("Sorting completed.");
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
			
		} finally {
			finish = true;
		}
	}
	
	
	/**
	 * A <b>public</b> method to indicate the termination 
	 * of sorting operation.
	 * <p>
	 * It will set the terminate value to true, and invoke a call to terminate 
	 * the sorting operation.
	 */
	public void terminate() {
		terminate = true;
		alg.terminate();
	}
	
	
	/**
	 * A <b>public</b> method to indicate the completion 
	 * of sorting operation.
	 * <p>
	 * It will set the finish value to true.
	 */
	public boolean isCompleted() {
		return finish;
	}
}