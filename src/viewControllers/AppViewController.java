package viewControllers;

import models.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
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

	private PrintStream output = System.out;

	// Make recode of Model
	private Model theModel;

	// JPanel
	private JPanel backgroundPanel;
	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	private JPanel titlePanel;
	private JPanel contentPanel;
	private JPanel pageIndexPanel;

	// JLabel
	private JLabel titleLabel;
	private JLabel storeInfoLabel;
	
	// JButton
	
	// Image path
	private static final String BACKGROUND_IMAGE_PATH = "background_image.png";

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
		this.setTitle("Uncle Tetsu Reservation");
		this.setLocationByPlatform(true);
		// Center window in the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 4*3, dim.height / 2 - this.getSize().height / 4*3);
		this.setResizable(false);

		// Initialize all the panels
		backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
			    try {
					File pathToFile = new File(AppViewController.BACKGROUND_IMAGE_PATH);
					Image bgImage = ImageIO.read(pathToFile);
					g.drawImage(bgImage, 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		middlePanel = new JPanel();
		titlePanel = new JPanel();
		contentPanel = new JPanel();
		pageIndexPanel = new JPanel();
		
		// Set all panels' background color to white
		titlePanel.setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		leftPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		middlePanel.setBackground(Color.WHITE);
		pageIndexPanel.setBackground(Color.WHITE);
		this.getContentPane().setBackground(Color.WHITE);
		
		// Set layout for those panels that will contain other components.
		titlePanel.setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		middlePanel.setLayout(new BorderLayout());
		pageIndexPanel.setLayout(new BorderLayout());
		this.getContentPane().setLayout(new BorderLayout());
		// Set panels' preferred sizes to make GUI looks nice.
		titlePanel.setPreferredSize(new Dimension(500, 265));
		contentPanel.setPreferredSize(new Dimension(580, 100));
		leftPanel.setPreferredSize(new Dimension(50, 384));
		rightPanel.setPreferredSize(new Dimension(50, 384));
		pageIndexPanel.setPreferredSize(new Dimension(580, 30));

		// Initialize titleLabel component
		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
		titleLabel.setText(theModel.getWelcomeMsg());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// Add titleLabel on titlePanel with position.
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		// Add three panels on middlePanel with positions
		middlePanel.add(titlePanel, BorderLayout.NORTH);
		middlePanel.add(contentPanel, BorderLayout.CENTER);
		middlePanel.add(pageIndexPanel, BorderLayout.PAGE_END);

		// Add three main panels on content pane with positions
		/*this.getContentPane().add(leftPanel, BorderLayout.WEST);
		this.getContentPane().add(rightPanel, BorderLayout.EAST);
		this.getContentPane().add(middlePanel, BorderLayout.CENTER);*/
		this.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

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
		if(theModel.getCurrentState() == theModel.STATE_UNASSIGNED) {
			
		}
		
		if(theModel.getCurrentState() == theModel.INIT_STATE) {
			
		}
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
