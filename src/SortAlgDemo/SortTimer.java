package SortAlgDemo;


import java.util.*;


/**
 * The <code>SortTimer</code> class is a subclass of the Thread class.  
 * Therefore the timer itself inherits all the behavior and methods of 
 * a generic Thread Object, as a result to implement the "multi-threading" 
 * feature for the software itself. Note that this class should possess 
 * some public access methods to allow external call (from the SortVisualizer) 
 * such that the SortVisualizer could access its current state.
 * <br>
 * This class acts as the controller thread which have access to the SortHandler, 
 * and therefore user's input from the GUI can be converted into signal which is 
 * then passed into the SortHandler thread.
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
public class SortTimer extends Thread {
	/**
	 * A <b>private</b> SortHandler object for the control of sorting operations 
	 * invoked.
	 */
	private SortHandler sh;
	/**
	 * A <b>private</b> SortVisualizer object as a referenced for interaction.
	 */
	private SortVisualizer sv;
	/**
	 * A <b>private</b> boolean value to indicate if the sorting operation has 
	 * been terminated, default value is false.
	 */
	private boolean stop = false;
	
	
	/**
	 * Create an instance of SortTimer.
	 * <p>
	 * @param sv A SortVisualizer object.
	 * @param sh A SortHandler object.
	 */
	public SortTimer(SortVisualizer sv, SortHandler sh) {
		this.sv = sv;
		this.sh = sh;
	}
	
	
	/**
	 * A <b>public</b> run method being implemented for multi-threading.
	 * <p>
	 * When invoked, it also calls the start of sorting operation. And every 
	 * 10 milli-seconds it will check if there has been any terminate signal 
	 * being sent by user. If yes, it will stop the sorting operation via 
	 * Thread interruption.
	 */
	public void run() {
		try {
			//Record the Start Time
			Calendar timeBefore = Calendar.getInstance();
			
			sh.start();
			
			while (sh.isCompleted() == false) {
				try {
					sleep(10); //wait for 10 milli-seconds, then re-check
				} catch (InterruptedException ie) {
					if (stop == true) {
						sh.terminate();
					}
					
					//System.out.println("SortTimer interrupted"); //Debugger
					sh.interrupt();
				}
			}
			
			//Record the Stop Time
			Calendar timeAfter = Calendar.getInstance();
			
			sv.finishSorting(timeAfter.getTimeInMillis() - timeBefore.getTimeInMillis());
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	/**
	 * A <b>public</b> method to indicate the termination 
	 * of sorting operation.
	 * <p>
	 * It will set the stop value to true.
	 */
	public void terminate() {
		stop = true;
	}
}