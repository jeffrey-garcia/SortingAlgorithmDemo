package SortAlgDemo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.applet.*;

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

import java.io.*;
import java.util.*;
import java.net.*;


/**
 * The <code>SortVisualizer</code> class is the main GUI. It is capable 
 * to handle all users's input, and displays graphical sorting results. 
 * It is the top level ancestor of all the other classes (the entry point of 
 * the software). It will initialize a SortTimer and a SortHandler object 
 * when users trigger a new sorting demo as its global variable, this class 
 * should have full access to the timer and handler so as to gain control 
 * on the execution of the entire sorting process.
 * <br>
 * For example, pause the sorting, continue the sorting, and terminate the 
 * sorting.
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
public class SortVisualizer extends JApplet implements ActionListener, WindowListener {
	/**
	 * A <b>private</b> JPanel which is the upper portion of the GUI. 
	 */
	private JPanel jpTop;
	/**
	 * A <b>private</b> JPanel which is the main portion of the GUI. 
	 */
	private JPanel jpMain;
	/**
	 * A <b>private</b> JPanel which is the portion of the GUI that display 
	 * current configuration.
	 */
	private JPanel jpConfig;
	/**
	 * A <b>private</b> JPanel which is the portion of the GUI that display 
	 * the sorting process.
	 */
	private JPanel jpMain_center;
	
	/**
	 * A <b>private</b> JComboBox which allow user to select the preferred 
	 * sorting algorithm.
	 */
	private JComboBox jcbSortAlg;
	/**
	 * A <b>private</b> JComboBox which allow user to select the preferred 
	 * number of data being sorted.
	 */
	private JComboBox jcbDataNo;
	/**
	 * A <b>private</b> JComboBox which allow user to select the preferred 
	 * initial ordering of data being sorted.
	 */
	private JComboBox jcbInitOrder;
	/**
	 * A <b>private</b> JComboBox which allow user to select the preferred 
	 * sorting speed.
	 */
	private JComboBox jcbSortSpeed;
	
	/**
	 * A <b>private</b> JButton which will open the configuration menu when 
	 * user click on it.
	 */
	private JButton jbtnConfig;
	/**
	 * A <b>private</b> JButton which will open the help menu when user click 
	 * on it.
	 */
	private JButton jbtnHelp;
	/**
	 * A <b>private</b> JButton which will open the API menu when user click 
	 * on it.
	 */
	private JButton jbtnAPI;
	/**
	 * A <b>private</b> JButton which will terminate the current sorting 
	 * operation when user click on it.
	 */
	private JButton jbtnStop;
	/**
	 * A <b>private</b> JButton which will pause the current sorting operation 
	 * when user click on it.
	 */
	private JButton jbtnPause;
	/**
	 * A <b>private</b> JButton which will start the sorting operation with 
	 * the selected config when user click on it.
	 */
	private JButton jbtnSetConfig;
	
	/**
	 * A <b>private</b> JLabel which will display the current config.
	 */
	private JLabel jlbCurrentConfig;
	/**
	 * A <b>private</b> JLabel which will display the system message.
	 */
	private JLabel jlbSystemMsg;
	
	/**
	 * A <b>private</b> JDialog which holds the configuration menu.
	 */
	private JDialog jdgConfig;
	
	/**
	 * A <b>private</b> String which display the software name.
	 */
	private String appName = "Sorting Algorithm Visualizer v1.1";
	/**
	 * A <b>private</b> String which display the software description.
	 */
	private String appDesc = "Bachelor of Computing (E-Commerce) - SDPM Course Project" + 
								"<br>Copyrights &copy; 2005 All Rights Reserved (written by Jeffrey Garcia)";
	
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
	 * A <b>private</b> SortTimer object.
	 */
	private SortTimer st;
	
	
	/**
	 * Create an instance of SortVisualizer.
	 */
	public SortVisualizer() {
		//=== Initialize all the JComboBox ===
		jcbSortAlg = new JComboBox();
		jcbSortAlg.setToolTipText("Please select the sorting algorithm to demo.");
		jcbSortAlg.addItem("");
		jcbSortAlg.addItem("Insertion Sort");
		jcbSortAlg.addItem("Shell Sort");
		jcbSortAlg.addItem("Quick Sort");
		jcbSortAlg.addItem("Merge Sort   (Double Storage)");
		jcbSortAlg.addItem("Merge Sort   (In-place Sorting)");
		
		jcbDataNo = new JComboBox();
		jcbDataNo.setToolTipText("Select the number of data to sort");
		jcbDataNo.addItem("");
		for (int i=3; i<=20; i++) {
			jcbDataNo.addItem("" + i);
		}
		jcbDataNo.addItem("50");
		jcbDataNo.addItem("100");
		jcbDataNo.addItem("500");
		jcbDataNo.addItem("1000");
		
		jcbInitOrder = new JComboBox();
		jcbInitOrder.setToolTipText("Select the initial orderomg of data");
		jcbInitOrder.addItem("");
		jcbInitOrder.addItem("Increasing order");
		jcbInitOrder.addItem("Decreasing order");
		jcbInitOrder.addItem("Random order");
		jcbInitOrder.addItem("Nearly sorted order");
		
		jcbSortSpeed = new JComboBox();
		jcbSortSpeed.setToolTipText("Select the speed of sorting");
		jcbSortSpeed.addItem("");
		jcbSortSpeed.addItem("Step");
		jcbSortSpeed.addItem("Slow   5 sec/sort");
		jcbSortSpeed.addItem("Medium   2.5 sec/sort");
		jcbSortSpeed.addItem("Fast   1 sec/sort");
		jcbSortSpeed.addItem("Non-Stop");
		//====================================
	}
	
	
	/**
	 * A <b>public</b> method to initialize 
	 * the GUI.
	 */
	public void init() {
		//setupTheme();
		
		jpTop = new JPanel();
		jpTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpTop.setBackground(Color.WHITE);
		jpTop.setPreferredSize(new Dimension(400,120));
		jpTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpTop.add(new JLabel(new ImageIcon(this.getClass().getResource("image/unisa.jpg"))));
		
			JPanel jpTop_right = new JPanel();
			jpTop_right.setLayout(new BorderLayout());
			jpTop_right.setBackground(Color.WHITE);
				JLabel jlbTitle = new JLabel("<html><font face='Verdana' color='#000080' size='6'>" + appName + "</font></html>");
				JLabel jlbSubTitle = new JLabel("<html><font face='Arial' color='#000080' size='3'>" + appDesc + "</font></html>");
			jpTop_right.add(jlbTitle, BorderLayout.CENTER);
			jpTop_right.add(jlbSubTitle, BorderLayout.SOUTH);
			
		jpTop.add(jpTop_right);
		
		jpMain = new JPanel();
		jpMain.setBackground(Color.WHITE);
		jpMain.setLayout(new BorderLayout());
		jpMain.setBorder(
                BorderFactory.createCompoundBorder(
                	BorderFactory.createTitledBorder("Main menu"),
                	BorderFactory.createEmptyBorder(5,5,5,5)
				)
		);
		
			JPanel jpMain_top = new JPanel();
			jpMain_top.setBackground(Color.WHITE);
			jpMain_top.setLayout(new FlowLayout(FlowLayout.LEFT));
				jcbSortAlg.addActionListener(this);
				
				jbtnConfig = new JButton("Begin");
				jbtnConfig.setToolTipText("Please click this button to start demo.");
				jbtnConfig.addActionListener(this);
				
				jbtnHelp = new JButton("Help");
				jbtnHelp.setToolTipText("Please click this button to view the help.");
				jbtnHelp.addActionListener(this);
				jbtnHelp.setEnabled(false);
				
				jbtnAPI = new JButton("API");
				jbtnAPI.setToolTipText("Please click this button to read the API.");
				jbtnAPI.addActionListener(this);
				jbtnAPI.setEnabled(true);
				
			jpMain_top.add(new JLabel("Select Sorting Algorithm: "));
			jpMain_top.add(jcbSortAlg);
			jpMain_top.add(jbtnConfig);
			jpMain_top.add(jbtnHelp);
			jpMain_top.add(jbtnAPI);
			
			//This is the place where we display the current configuration of demo
			jpConfig = new JPanel();
			jpConfig.setBackground(Color.WHITE);
			jpConfig.setLayout(new FlowLayout(FlowLayout.LEFT));
			jlbCurrentConfig = new JLabel("<html>" + 
									"<font color='#333333'>Sorting algorithm:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbSortAlg.getSelectedItem() + "</font>" + 
									"<br><br>" + 
									"<font color='#333333'>No. of sort data:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbDataNo.getSelectedItem() + "</font>" + 
									"<br><br>" + 
									"<font color='#333333'>Initial data order:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbInitOrder.getSelectedItem() + "</font>" +
									"<br><br>" + 
									"<font color='#333333'>Speed of sorting:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbSortSpeed.getSelectedItem() + "</font>" +
									"</html>");
			jpConfig.add(jlbCurrentConfig);
			
			JScrollPane jspMain_west = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jspMain_west.setBackground(Color.WHITE);
			jspMain_west.setPreferredSize(new Dimension(140,200));
			jspMain_west.getViewport().add(jpConfig);
			jspMain_west.setBorder(
                BorderFactory.createCompoundBorder(
                	BorderFactory.createTitledBorder("Current Setting"),
                	BorderFactory.createEmptyBorder(5,5,5,5)
				)
			);
			
			jpMain_center = new JPanel();
			jpMain_center.setBackground(Color.WHITE);
			jpMain_center.setLayout(new BorderLayout());
			jpMain_center.setMinimumSize(new Dimension(350,200));
			jpMain_center.setBorder(
                BorderFactory.createCompoundBorder(
                	BorderFactory.createTitledBorder("Display Area"),
                	BorderFactory.createEmptyBorder(5,5,5,5)
				)
			);
			
			JSplitPane jsptMain_center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			jsptMain_center.setContinuousLayout(true);
			jsptMain_center.setOneTouchExpandable(true);
			jsptMain_center.add(jspMain_west);
			jsptMain_center.add(jpMain_center);
			
			JPanel jpMain_bottom = new JPanel();
			jpMain_bottom.setBackground(Color.WHITE);
			jpMain_bottom.setLayout(new FlowLayout(FlowLayout.LEFT));
				jbtnPause = new JButton("Pause");
				jbtnPause.setToolTipText("Please click this button to pause the demo.");
				jbtnPause.addActionListener(this);
				jbtnPause.setEnabled(false);
				
				jbtnStop = new JButton("Stop");
				jbtnStop.setToolTipText("Please click this button to terminate the demo.");
				jbtnStop.addActionListener(this);
				jbtnStop.setEnabled(false);
				
				jlbSystemMsg = new JLabel("<html><font color='#000080' face='Arial'>System Ready.</html>");
				
			jpMain_bottom.add(new JLabel("Display option:"));
			jpMain_bottom.add(jbtnPause);
			jpMain_bottom.add(jbtnStop);
			jpMain_bottom.add(jlbSystemMsg);
			
		jpMain.add(jpMain_top, BorderLayout.NORTH);
		//jpMain.add(jpMain_center, BorderLayout.CENTER);
		jpMain.add(jsptMain_center, BorderLayout.CENTER);
		jpMain.add(jpMain_bottom, BorderLayout.SOUTH);
		
		getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jpTop, BorderLayout.NORTH);
        getContentPane().add(jpMain, BorderLayout.CENTER);
        
        setVisible(true);
	}
	
	
	/**
	 * A <b>public</b> method to return the 
	 * name of the software.
	 * <p>
	 * @return name of the software.
	 */
	public String getAppName() {
		return appName;
	}
	
	
	/**
	 * A <b>public</b> method to receive the user's input 
	 * signal from GUI components
	 * <p>
	 * @param ae the corresponding ActionEvent
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtnConfig) {
			if (jcbSortAlg.getSelectedItem().equals("")==true) {
				warning("Please select your target sort algorithm first!");
			} else {
				configDemo();
			}
			
		} else if (ae.getSource() == jbtnAPI) {
			try {
		        AppletContext context = getAppletContext();
		        context.showDocument(new URL("https://googledrive.com/host/0BwP5Ki5tO2LsbGk0OFZUbEFPbUk"),"_blank");
		    	
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			
		} else if (ae.getSource() == jbtnHelp) {
			
		} else if (ae.getSource() == jbtnStop) {
			boolean stop = warning("Are you sure to terminate the sorting?<br>Click [OK] to stop, [CANCEL] to abort");
			
			if (stop == true) {
				jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Sorting aborted.</html>");
				st.terminate();
				st.interrupt();
			}
			
		} else if (ae.getSource() == jbtnPause) {
			//Dynamically change the button text
			if (jbtnPause.getText().equals("Pause")) {
				jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Sorting paused, click Play to continue</html>");
				jbtnPause.setText("Play");
			} else if (jbtnPause.getText().equals("Play")) {
				jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Sorting in progress...</html>");
				jbtnPause.setText("Pause");
			}
			
			st.interrupt();
			
		} else if (ae.getSource() == jbtnSetConfig) {
			if (jcbDataNo.getSelectedItem().equals("")==true) {
				warning("Please select the no. of data to sort!");
			} else if (jcbInitOrder.getSelectedItem().equals("")==true) {
				warning("Please select the initial order of data!");
			} else if (jcbSortSpeed.getSelectedItem().equals("")==true) {
				warning("Please select the sorting speed!");
			} else {
				jdgConfig.hide();
				jdgConfig.setVisible(false);
				jdgConfig.dispose();
				
				beginSorting();
			}
		}
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window opened state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowOpened(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window closing state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowClosing(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window closed state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowClosed(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window iconified state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowIconified(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window de-iconified state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowDeiconified(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window activated state.
	 * <p>
	 * If the WindowEvent is came from the main GUI - 
	 * SortVisualizer and if the jdgConfig is not null, 
	 * we will display the configuration menu.
	 * <p>
	 * The part is relevant since we observe a behavior 
	 * that if the browser window (which contain the JApplet) 
	 * is minimized, then the JDialog box invoked by the 
	 * JApplet will not be activated after the browser window 
	 * is maximized, this could cause problem that our user 
	 * may not get back the control properly especially if 
	 * the JDialog box is set to Modal.
	 * <p>
	 * Not sure if this is a bug of the JDialog in JDK.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowActivated(WindowEvent we) {
		if (we.getSource() == this) {
			if (jdgConfig != null) {
				jdgConfig.show();
			}
		}
	}
	
	
	/**
	 * A <b>public</b> method to put a listener on 
	 * the window de-activated state.
	 * <p>
	 * @param we the corresponding WindowEvent
	 */
	public void windowDeactivated(WindowEvent we) {
		//
	}
	
	
	/**
	 * A <b>public</b> method to display a warning 
	 * message.
	 * <p>
	 * @return true if the OK button of the message box is clicked, 
	 *  else return false if the CANCEL button is clicked.
	 * @param opt the message text to be shown.
	 */
	public boolean warning(String opt) {	
		JOptionPane jop;
		JDialog jdg;
		
		opt = opt.replaceAll("\n","<br>");
		String temp = "<html><font color='#333333'>" + opt + "</font></html>";
		
		jop = new JOptionPane(temp,
									JOptionPane.WARNING_MESSAGE,
									JOptionPane.OK_CANCEL_OPTION);
		
		jdg = jop.createDialog(this,"Warning!");
		
		jdg.setLocationRelativeTo(null);
		jdg.setVisible(true);
		
		if (jop.getValue() != null) {
			int value = ((Integer)jop.getValue()).intValue();
			
			if (value==JOptionPane.YES_OPTION) {
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	
	/**
	 * A <b>private</b> method to setup the color theme of the 
	 * GUI.
	 * <p>
	 * It will instantiate the SortVisualizer_UIColor and apply this 
	 * theme into the GUI.
	 */
	private void setupTheme() {
		SortVisualizer_UIColor theme;
		
		try {
			theme = new SortVisualizer_UIColor();
			theme.setPrimary1(new ColorUIResource(150, 150, 150)); //Menu Border
			theme.setPrimary2(new ColorUIResource(100, 100, 100)); //Scrollbar Background
			theme.setPrimary3(new ColorUIResource(190, 190, 200)); //Scrollbar Foreground + border
			
			theme.setSecondary2(new ColorUIResource(70, 70, 70)); //Panel Background, Tabbed Pane Color #Pressed Button
			theme.setSecondary3(new ColorUIResource(130, 130, 130)); //Panel Foreground
			
			if (UIManager.getLookAndFeel().getName().indexOf("Metal") >= 0) {
				MetalLookAndFeel.setCurrentTheme(theme);
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
			}
			
			/*
			Font uiFont = new Font ("Arial", Font.PLAIN, 11);
			Enumeration en = UIManager.getDefaults().keys();
			
			while (en.hasMoreElements()) {
				String key = en.nextElement().toString();
				
				if (key.indexOf("font") >= 0) {
					//System.out.println("key: "+ key +" | value: " + UIManager.getDefaults().get(key)); //Debugger
					UIManager.getDefaults().put(key, uiFont);
				}
			}
			*/
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	/**
	 * A <b>private</b> method to display the configuration menu.
	 */
	private void configDemo() {
		try {
			jdgConfig = new JDialog(JOptionPane.getFrameForComponent(this), "Demo Settings", true);
			jdgConfig.getContentPane().setLayout(new BorderLayout());
			jdgConfig.setBackground(Color.WHITE);
			
				JPanel jpCenter = new JPanel();
				jpCenter.setLayout(new BorderLayout());
				jpCenter.setBackground(Color.WHITE);
					JPanel jpCenter_1 = new JPanel();
					jpCenter_1.setLayout(new GridLayout(3,1,5,5));
					jpCenter_1.setBackground(Color.WHITE);
					jpCenter_1.add(new JLabel(" No. of data to sort:  "));
					jpCenter_1.add(new JLabel(" Initial data order:  "));
					jpCenter_1.add(new JLabel(" Sorting speed:  "));
					
					JPanel jpCenter_2 = new JPanel();
					jpCenter_2.setLayout(new GridLayout(3,1,5,5));
					jpCenter_2.setBackground(Color.WHITE);
					jpCenter_2.add(jcbDataNo);
					jpCenter_2.add(jcbInitOrder);
					jpCenter_2.add(jcbSortSpeed);
				jpCenter.add(jpCenter_1, BorderLayout.CENTER);
				jpCenter.add(jpCenter_2, BorderLayout.EAST);
				jpCenter.setBorder(
                	BorderFactory.createCompoundBorder(
                		BorderFactory.createTitledBorder("Select your preferred settings"),
                		BorderFactory.createEmptyBorder(5,5,5,5)
					)
				);
				
				JPanel jpBottom = new JPanel();
				jpBottom.setLayout(new BorderLayout());
				jpBottom.setBackground(Color.WHITE);
					jbtnSetConfig = new JButton("OK and start sorting");
					jbtnSetConfig.setToolTipText("Click this button to begin sorting process");
					jbtnSetConfig.addActionListener(this);
				jpBottom.add(jbtnSetConfig);
				
			jdgConfig.getContentPane().add(jpCenter, BorderLayout.CENTER);
			jdgConfig.getContentPane().add(jpBottom, BorderLayout.SOUTH);
			
			jdgConfig.setResizable(false);
			jdgConfig.pack();
			jdgConfig.setLocationRelativeTo(null);
			
			jdgConfig.show();
			
		} catch (Exception exc) {
			exc.printStackTrace();
			warning(exc.getMessage());
		}
	}
	
	
	/**
	 * A <b>public</b> method to begin the sorting operation.
	 * <p>
	 * When invoked, it will instantiate a SortTimer object which 
	 * will be later used to track everything happend during the 
	 * sorting operation.
	 */
	public void beginSorting() {
		try {
			if (jcbSortAlg.getSelectedItem().equals("Insertion Sort")) {
				sortingAlg = 0;
			} else if (jcbSortAlg.getSelectedItem().equals("Shell Sort")) {
				sortingAlg = 1;
			} else if (jcbSortAlg.getSelectedItem().equals("Quick Sort")) {
				sortingAlg = 2;
			} else if (jcbSortAlg.getSelectedItem().equals("Merge Sort   (In-place Sorting)")) {
				sortingAlg = 3;
			} else if (jcbSortAlg.getSelectedItem().equals("Merge Sort   (Double Storage)")) {
				sortingAlg = 3;
			}
			
			sortingDataNo = Integer.parseInt(jcbDataNo.getSelectedItem() + "");
			
			if (jcbInitOrder.getSelectedItem().equals("Increasing order")) {
				sortingOrder = 0;
			} else if (jcbInitOrder.getSelectedItem().equals("Decreasing order")) {
				sortingOrder = 1;
			} else if (jcbInitOrder.getSelectedItem().equals("Random order")) {
				sortingOrder = 2;
			} else if (jcbInitOrder.getSelectedItem().equals("Nearly sorted order")) {
				sortingOrder = 3;
			}
			
			if (jcbSortSpeed.getSelectedItem().equals("Step")) {
				sortingSpeed = 0;
				
				//Set the text of jbtnPause to Next
				jbtnPause.setText("Next");
				
			} else if (jcbSortSpeed.getSelectedItem().equals("Slow   5 sec/sort")) {
				sortingSpeed = 1;
				
				//Set the text of jbtnPause to Pause
				jbtnPause.setText("Pause");
				
			} else if (jcbSortSpeed.getSelectedItem().equals("Medium   2.5 sec/sort")) {
				sortingSpeed = 2;
				
				//Set the text of jbtnPause to Pause
				jbtnPause.setText("Pause");
				
			} else if (jcbSortSpeed.getSelectedItem().equals("Fast   1 sec/sort")) {
				sortingSpeed = 3;
				
				//Set the text of jbtnPause to Pause
				jbtnPause.setText("Pause");
			} else if (jcbSortSpeed.getSelectedItem().equals("Non-Stop")) {
				sortingSpeed = 4;
				
				//Set the text of jbtnPause to Pause
				jbtnPause.setText("Pause");
			}
			
			jlbCurrentConfig.setText("<html>" + 
									"<font color='#333333'>Sorting algorithm:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbSortAlg.getSelectedItem() + "</font>" + 
									"<br><br>" + 
									"<font color='#333333'>No. of sort data:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbDataNo.getSelectedItem() + "</font>" + 
									"<br><br>" + 
									"<font color='#333333'>Initial data order:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbInitOrder.getSelectedItem() + "</font>" +
									"<br><br>" + 
									"<font color='#333333'>Speed of sorting:</font>" + 
									"<br>" + 
									"<font color='#666666'>" + jcbSortSpeed.getSelectedItem() + "</font>" +
									"</html>");
			
			//----- Clear the main Panel -----
			jpMain_center.removeAll();
			jpMain_center.updateUI();
			jpMain_center.revalidate();
			//--------------------------------
			
			//Initialize and start the sorting handler
			st = new SortTimer(this, new SortHandler(this, sortingAlg, sortingDataNo, sortingOrder, sortingSpeed));
			st.start();
			
			//Dim the begin button
			jbtnConfig.setEnabled(false);
			jbtnPause.setEnabled(true);
			jbtnStop.setEnabled(true);
			
			if (jbtnPause.getText().equals("Next")) {
				jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Press Next to continue next sort...</html>");
			} else if (jbtnPause.getText().equals("Pause")) {
				jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Sorting in progress...</html>");
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
			warning(exc.getMessage());
		}
	}
	
	
	/**
	 * A <b>public</b> method to indicate the completion of the 
	 * sorting operation.
	 * <p>
	 * Upon the completion, it will also invoke a Garbage Collection to 
	 * try release un-used memory.
	 * <p>
	 * @param elapsedTime the total time spent (of primitive data type long) 
	 *  on the sorting operation .
	 */
	public void finishSorting(long elapsedTime) {
		//Un-dim the begin button
		jbtnConfig.setEnabled(true);
		jbtnPause.setEnabled(false);
		jbtnStop.setEnabled(false);
		jlbSystemMsg.setText("<html><font color='#000080' face='Arial'>Sorting Completed in " + elapsedTime + " (milli-second)</html>");
		
		//This part is for housekeeping to free up some memory
		System.gc();
	}
	
	
	/**
	 * A <b>public</b> method to display the current sorting operation 
	 * in bar charts.
	 * <p>
	 * It will instantiate a barChart object which returns a JPanel contain the 
	 * graphics to be display.
	 * <p>
	 * @param c the data container which holds the current ordering of the data being sorted.
	 * @param comparisonCount the counter which holds the no. of comparison occured.
	 * @param swapCount the counter which holds the no. of data swapping occured.
	 */
	public void drawChart(Comparable [] c, int comparisonCount, int swapCount) {
		BarChartPanel barChart = new BarChartPanel(c, comparisonCount, swapCount);
		
		//----- Put the JPanel into a JScrollPane -----
		JScrollPane jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//jsp.setPreferredSize(new Dimension(450,250));
		jsp.getViewport().add(barChart);
		//---------------------------------------------
		
		//----- Add all the ScrollPanes to the main Panel -----
		jpMain_center.removeAll();
		jpMain_center.add(jsp, BorderLayout.CENTER);
		jpMain_center.updateUI();
		jpMain_center.revalidate();
		//-----------------------------------------------------
	}
}
