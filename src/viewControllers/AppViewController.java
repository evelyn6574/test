package viewControllers;

import models.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class encapsulates a GUI, a component of which is a view-controller
 * delegate. This object is an observer of the Model.
 * 
 * This GUI presents views that are tailored to the current state of the Model.
 * Changes to the view are driven by state changes to the Model. View
 * initializes with a start button. This action
 * launches the first state change to the Model.
 * 
 * @author Tong Wu - 212531380
 * @author Yiwei Wang - 213267026
 *
 */
public class AppViewController extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = 2L; // needed by serializers

	private PrintStream myOut = System.out;

	// Make recode of Model
	private Model theModel;

	// Initial Components
	private JPanel infoPanel;
	private JPanel buttonPanel;
	private JPanel leftEmptyPanel, middlePanel, rightEmptyPanel, bottomEmptyPanel;

	private JLabel theLabel;
	private JLabel positionLabel;
	private JButton startButton;
	private JButton restartButton;
	private List<JButton> buttonList;

	/**
	 * Creates the view-controller delegate
	 * 
	 * @param model
	 *            the Model
	 */
	public AppViewController(Model model) {
		theModel = model;

		// Set up some basic aspects of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) thisScreen.getWidth() / 2, (int) thisScreen.getHeight() / 2);
		this.setTitle("Color Game");
		this.setLocationByPlatform(true);
		// this methods asks the window manager to position the frame in the
		// centre of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 4*3, dim.height / 2 - this.getSize().height / 4*3);

		// Here we set up the GUI. Initialize all the panels.
		infoPanel = new JPanel();
		buttonPanel = new JPanel();
		leftEmptyPanel = new JPanel();
		rightEmptyPanel = new JPanel();
		middlePanel = new JPanel();
		bottomEmptyPanel = new JPanel();
		// Set all panels' background color to white
		infoPanel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		leftEmptyPanel.setBackground(Color.WHITE);
		rightEmptyPanel.setBackground(Color.WHITE);
		middlePanel.setBackground(Color.WHITE);
		bottomEmptyPanel.setBackground(Color.WHITE);
		this.getContentPane().setBackground(Color.WHITE);
		// Set layout for those panels that will contain other components.
		infoPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new BorderLayout());
		middlePanel.setLayout(new BorderLayout());
		bottomEmptyPanel.setLayout(new BorderLayout());
		this.getContentPane().setLayout(new BorderLayout());
		// Set panels' preferred sizes to make GUI looks nice.
		infoPanel.setPreferredSize(new Dimension(500, 265));
		buttonPanel.setPreferredSize(new Dimension(580, 100));
		leftEmptyPanel.setPreferredSize(new Dimension(50, 384));
		rightEmptyPanel.setPreferredSize(new Dimension(50, 384));
		bottomEmptyPanel.setPreferredSize(new Dimension(580, 30));

		// Initialize theLabel component
		theLabel = new JLabel();
		theLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
		theLabel.setText(theModel.getWelcomeMsg());
		theLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Add theLabel on infoPanel with position.
		infoPanel.add(theLabel, BorderLayout.CENTER);

		// Initialize startButton component
		startButton = new JButton("Start");
		startButton.setEnabled(true);
		startButton.setPreferredSize(new Dimension(200, 100));
		// Add startButton on buttonPanel with position
		buttonPanel.add(startButton, BorderLayout.CENTER);
		
		// Add three panels on middlePanel with positions
		middlePanel.add(infoPanel, BorderLayout.NORTH);
		middlePanel.add(buttonPanel, BorderLayout.CENTER);
		middlePanel.add(bottomEmptyPanel, BorderLayout.PAGE_END);

		// Add three main panels on content pane with positions
		this.getContentPane().add(leftEmptyPanel, BorderLayout.WEST);
		this.getContentPane().add(rightEmptyPanel, BorderLayout.EAST);
		this.getContentPane().add(middlePanel, BorderLayout.CENTER);

		// here we install this object (ActionListener) on the button so that we
		// may detect user actions that may be dispatched from it.
		startButton.addActionListener(this);

		this.setVisible(true);

		// this method asks the frame layout manager to size the frame so that
		// all its contents are at or above their preferred sizes
		this.pack();
		// make this component visible (do not assume that it will be visible by
		// default)
		this.setVisible(true);
	}

	@Override
	public Dimension getPreferredSize() {
		// find the dimensions of the screen and a dimension that is derive one
		// half of the size
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension targetSize = new Dimension((int) thisScreen.getWidth() / 4*3, (int) thisScreen.getHeight() / 4*3);
		return targetSize;
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * Build the initial question page, which is the next page of welcome page.
	 */
	public void initQuestionPanel() {
		this.getContentPane().repaint();
	}
	
	/**
	 * Build summary page, which is the next page of the last question.
	 */
	public void summaryQuestionPanel() {
		this.getContentPane().repaint();
	}

}
