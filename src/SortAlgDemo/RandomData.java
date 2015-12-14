package SortAlgDemo;


import java.util.Random;


/**
 * The <code>RandomData</code> class is a class that generates random float 
 * values for all sorting algorithms. In order to investigate the accuracy 
 * of various sorting operation performed by the software, this class plays 
 * an essential role to let us generate bulk input data for intensive 
 * debugging.
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
 * @see	<a href="../api/SortHandler.html">SortHandler</a>
 */
public class RandomData {
	/**
	 * A <b>private</b> Random object.
	 */
	private Random r;
	
	
	/**
	 * Create an instance of RandomData.
	 */
	public RandomData() {
		r = new Random();
	}
	
	
	/**
	 * A <b>public</b> method to return the randomized data set.
	 * @return an array of Comparable Object which store the data being sorted.
	 */
	public Comparable [] getData(int dataSize) {
		Comparable [] c = new Comparable [dataSize];
		
		for (int i=0; i<dataSize; i++) {
			c [i] = new Float(r.nextFloat());
		}
		
		return c;
	}
}