package SortAlgDemo;


import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import java.awt.*;


/**
 * The <code>SortVisualizer_UIColor</code> class is the color theme of the 
 * GUI. It controls the coloring of the software outlook.
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
 * @see	<a href="../api/SortVisualizer_UIColor.html">SortVisualizer_UIColor</a>
 */
public class SortVisualizer_UIColor extends DefaultMetalTheme {
	/**
	 * A <b>private final</b> String which holds the name of the theme.
	 */
	private final String name = "MutableMetalTheme";
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource primary1 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource primary2 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource primary3 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource secondary1 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource secondary2 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource secondary3 = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource black = null;
	/**
	 * A <b>private</b> ColourUIResource. 
	 */
	private ColorUIResource white = null;
	
	
	/**
	 * Create an instance of SortVisualizer_UIColor.
	 */
	public SortVisualizer_UIColor(){
		initColors();
	}
	
	/**
	 * A <b>protected</b> methods to set the first primary ColorUIResource.
	 */
	protected void setPrimary1( ColorUIResource primary1 ){
		this.primary1 = primary1;
	}
	
	
	/**
	 * A <b>protected</b> methods to set the second primary ColorUIResource.
	 */
	protected void setPrimary2( ColorUIResource primary2 ){
		this.primary2 = primary2;
	}
	
	
	/**
	 * A <b>protected</b> methods to set the third primary ColorUIResource.
	 */
	protected void setPrimary3( ColorUIResource primary3 ){
		this.primary3 = primary3;
	}
	
	
	/**
	 * A <b>protected</b> methods to set the first secondary ColorUIResource.
	 */
	protected void setSecondary1( ColorUIResource secondary1 ){
		this.secondary1 = secondary1;
	}
	
	
	/**
	 * A <b>protected</b> methods to set the second secondary ColorUIResource.
	 */
	protected void setSecondary2( ColorUIResource secondary2 ){
		this.secondary2 = secondary2;
	}
	
	
	/**
	 * A <b>protected</b> methods to set the third secondary ColorUIResource.
	 */
	protected void setSecondary3( ColorUIResource secondary3 ){
		this.secondary3 = secondary3;
	}
	
	
	/**
	 * A <b>protected</b> utility method to set the first primary 
	 * ColorUIResource values with a Color object
	 */
	protected void setPrimary1( Color primary1 ){
		this.primary1 = new ColorUIResource( primary1 );
	}
	
	
	/**
	 * A <b>public</b> method to set the name of the theme
	 */
	public String getName() { return name; }
	
	
	/**
	 * A <b>protected</b> method to get the first primary 
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object primary1.
	 */
	protected ColorUIResource getPrimary1() {
		return primary1;
	}
	
	
	/**
	 * A <b>protected</b> method to get the second primary 
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object primary2.
	 */
	protected ColorUIResource getPrimary2() {
		return new ColorUIResource(Color.GRAY);
	}
	
	
	/**
	 * A <b>protected</b> method to get the third primary 
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object primary3.
	 */
	protected ColorUIResource getPrimary3() {
		return primary3;
	}
	
	
	/**
	 * A <b>protected</b> method to get the first secondary 
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object secondary1.
	 */
	protected ColorUIResource getSecondary1() {
		return secondary1;
	}
	
	
	/**
	 * A <b>protected</b> method to get the second secondary  
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object secondary2.
	 */
	protected ColorUIResource getSecondary2() {
		return new ColorUIResource(Color.lightGray);
	}
	
	
	/**
	 * A <b>protected</b> method to get the third secondary  
	 * ColorUIResource.
	 *
	 * @return the ColorUIResource object secondary3.
	 */
	protected ColorUIResource getSecondary3() {
		return new ColorUIResource(Color.WHITE);
	}
	
	
	/**
	 * A <b>protected</b> method to get the Black   
	 * ColorUIResource.
	 *
	 * @return a ColorUIResource object.
	 */
	protected ColorUIResource getBlack() {
		return new ColorUIResource(Color.BLUE.darker());
	}
	
	
	/**
	 * A <b>protected</b> method to get the White   
	 * ColorUIResource.
	 *
	 * @return a ColorUIResource object.
	 */
	protected ColorUIResource getWhite() {
		return new ColorUIResource(Color.WHITE);
	}
	
	
	/**
	 * A <b>private</b> method to initialize all colors to be the same 
	 * as in the current MetalLookAndFeel.
	 **/
	private void initColors() {
		MetalLookAndFeel lf = null;
		
		try{
			if (UIManager.getLookAndFeel().getName().indexOf("Metal") >= 0) {
				lf = (MetalLookAndFeel) UIManager.getLookAndFeel();
				
				primary1 = lf.getPrimaryControlDarkShadow();
				primary2 = lf.getDesktopColor();
				primary3 = lf.getPrimaryControl();
				secondary1 = lf.getControlDarkShadow();
				secondary2 = lf.getControlShadow();
				secondary3 = lf.getControl();
				black = lf.getControlHighlight();
				white = lf.getUserTextColor();
			}
			
		} catch( Exception e ){
			e.printStackTrace();
		}
	}
}
