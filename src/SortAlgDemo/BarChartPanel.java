package SortAlgDemo;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * The <code>BarChartPanel</code> class is a class extending the JPanel class.   
 * Its major objective is help to convert the sorted data (Array of Objects) 
 * into Graphics form (Bar Chart) which could be displayed in the GUI, such 
 * that user could experience the actual data movement with each sorting step.
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
public class BarChartPanel extends JPanel {
	/**
	 * A <b>private</b> array of Comparable object, the data container which 
	 * store the data being sorted.
	 */
	private Comparable [] c;
	/**
	 * A <b>private</b> integer which holds the total number comparison occured 
	 * in the sorting
	 */
	private int comparisonCount;
	/**
	 * A <b>private</b> integer which holds the total number of data swapping 
	 * occured in the sorting
	 */
	private int swapCount;
	/**
	 * A <b>private final</b> integer which presets the width of each bar in 
	 * the bar chart of 5, this value can't be modified.
	 */
	private final int barWidth = 5;
	
	
	/**
	 * Create an instance of BarChartPanel.
	 * <p>
	 * <p>
	 * @param c the data container which holds the current ordering of the data being sorted.
	 * @param comparisonCount the counter which holds the no. of comparison occured.
	 * @param swapCount the counter which holds the no. of data swapping occured.
	 */
	public BarChartPanel(Comparable [] c, int comparisonCount, int swapCount) {
		this.c = c;
		this.comparisonCount = comparisonCount;
		this.swapCount = swapCount;
	}
	
	
	/**
	 * A <b>public</b> method to paint the bar charts of the sorting operation.
	 * @param G the Graphics which will be used to paint the charts.
	 */
	public void paintComponent(Graphics G) {
		try {
			//=== Must clear the screen first ===
			super.setBackground(Color.white);
			G.clearRect(0,0,getWidth(),getHeight());
			//===================================
			
			int height = 200;
			int width = barWidth * c.length;
			
			int curr_x = 0;
			int curr_y = 0;
			
			for (int i=0; i<c.length; i++) {
				int y = (int)(((Float)c[i]).floatValue() * 100);
				//System.out.println(y); //Debugger
				
				//Draw the bar
				G.setColor(Color.BLUE);
				G.fillRect(curr_x, height-y, barWidth, 200);
				
				//Print the value
				//G.setColor(Color.darkGray);
				//G.setFont(new Font("Arial", Font.PLAIN, 11));
				//G.drawString((Float)c[i] + "", curr_x, height-y-5);
				
				//Print the Sorting Step Count
				G.setColor(Color.BLUE);
				G.setFont(new Font("Lucida Console", Font.BOLD, 14));
				G.drawString("No. of comparison: " + comparisonCount, 0, 0+20);
				G.drawString("No. of swap: " + swapCount, 0, 0+20+20);
				
				//curr_x += 35;
				curr_x += barWidth;
			}
			
			//System.out.println("Graphics Drawn."); //Debugger
			super.setPreferredSize(new Dimension(width,height));
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	/**
	 * A <b>public</b> method which update the current Graphics.
	 * @param G the Graphics which will be used to paint the charts.
	 */
	public void update(Graphics G) {
		//paintComponent(G);
	}
}
