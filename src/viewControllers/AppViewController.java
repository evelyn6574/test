package viewControllers;

import models.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	private JPanel storeInfoPanel;

	// JLabel
	private JLabel titleLabel;
	private JLabel subtitleLabel;
	private JLabel storeInfoLabel;
	private JLabel pageIndexLabel;
	
	// JButton
	private JButton nextPageButton;
	private JButton lastPageButton;
	
	// JFont
	private static final String GILL_SANS_ULTRA_BOLD_FONT = "Gill Sans Ultra Bold";
	private static final String HELVETICA_NEUE_FONT = "Helvetica Neue";
	private static final String CALIBRI_FONT = "Calibri";
	private static final String GILL_SANS_MT_FONT = "Gill Sans MT";
	
	// Color Integer
	private static final int BACKGROUND_COLOR = 16442734;
	private static final int APP_TITLE_LABEL_COLOR = 16230929;
	private static final int SUBTITLE_LABEL_COLOR = 10384394;
	private static final int STORE_INFO_LABEL_COLOR = 5592405;
	private static final int PAGE_TITLE_LABEL_COLOR = 16298787;
	
	// Image path
	private static final String BACKGROUND_IMAGE_PATH = "background_image.png";
	private static final String EMPTY_BUTTON_IMAGE_PATH = "empty_button_image.png";
	private static final String LAST_PAGE_BUTTON_IMAGE_PATH = "last_page_button_image.png";
	private static final String NEXT_PAGE_BUTTON_IMAGE_PATH = "next_page_button_image.png";
	private static final String PAGE_INDEX_1_IMAGE = "page_index_1_image.png";

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
		// Yi: more work needed here, app title text should be stored in Model.java
		this.setTitle(theModel.getAppTitle());
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
					Image resizebgImage = bgImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
					g.drawImage(resizebgImage, 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		leftPanel = new JPanel();
		middlePanel = new JPanel();
		rightPanel = new JPanel();
		titlePanel = new JPanel();
		contentPanel = new JPanel();
		pageIndexPanel = new JPanel();
		storeInfoPanel = new JPanel();
		
		// Set all panels' be not opaque
		//leftPanel.setBackground(Color.WHITE);
		//middlePanel.setBackground(Color.WHITE);
		//rightPanel.setBackground(Color.WHITE);
		//titlePanel.setBackground(Color.WHITE);
		//contentPanel.setBackground(Color.GRAY);
		//pageIndexPanel.setBackground(Color.PINK);
		//storeInfoPanel.setBackground(Color.PINK);
		//this.getContentPane().setBackground(Color.WHITE);
		leftPanel.setOpaque(false);
		rightPanel.setOpaque(false);
		middlePanel.setOpaque(false);
		titlePanel.setOpaque(false);
		contentPanel.setOpaque(false);
		pageIndexPanel.setOpaque(false);
		storeInfoPanel.setOpaque(false);
		
		// Set layout for those panels that will contain other components.
		middlePanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		titlePanel.setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		backgroundPanel.setLayout(new BorderLayout());
		pageIndexPanel.setLayout(new BorderLayout());
		storeInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().setLayout(new BorderLayout());
		
		// Set panels' preferred sizes to make GUI looks nice.
		leftPanel.setPreferredSize(new Dimension(150, 384));
		rightPanel.setPreferredSize(new Dimension(160, 384));
		titlePanel.setPreferredSize(new Dimension(500, 150));
		contentPanel.setPreferredSize(new Dimension(580, 100));
		pageIndexPanel.setPreferredSize(new Dimension(580, 30));

		// Initialize titleLabel component
		titleLabel = new JLabel();
		titleLabel.setFont(new Font(AppViewController.GILL_SANS_ULTRA_BOLD_FONT, Font.BOLD, 60));
		titleLabel.setForeground(new Color(AppViewController.APP_TITLE_LABEL_COLOR));
		// Yi: more work needed here, html text should be stored in Model.java
		titleLabel.setText(theModel.getWelcomeMsg());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(false);
		
		// Initialize subtitleLabel component
		subtitleLabel = new JLabel();
		subtitleLabel.setFont(new Font(AppViewController.CALIBRI_FONT, Font.PLAIN, 24));
		subtitleLabel.setForeground(new Color(AppViewController.SUBTITLE_LABEL_COLOR));
		// Yi: more work needed here, html text should be stored in Model.java
		subtitleLabel.setText(theModel.getTouchToStart());
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subtitleLabel.setOpaque(false);
		
		// Initialize storeInfoLabel component
		storeInfoLabel = new JLabel();
		storeInfoLabel.setFont(new Font(AppViewController.HELVETICA_NEUE_FONT, Font.PLAIN, 14));
		storeInfoLabel.setForeground(new Color(AppViewController.STORE_INFO_LABEL_COLOR));
		// Yi: more work needed here, html text should be stored in Model.java
		storeInfoLabel.setText(theModel.getAddressTime());
		storeInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeInfoLabel.setOpaque(false);
		
		// Add labels on panels with position.
		contentPanel.add(titleLabel, BorderLayout.NORTH);
		contentPanel.add(subtitleLabel, BorderLayout.CENTER);
		storeInfoPanel.add(storeInfoLabel);
		rightPanel.add(storeInfoPanel, BorderLayout.SOUTH);
		
		// Add three panels on middlePanel with positions
		middlePanel.add(titlePanel, BorderLayout.NORTH);
		middlePanel.add(contentPanel, BorderLayout.CENTER);
		middlePanel.add(pageIndexPanel, BorderLayout.PAGE_END);
		
		// Add three panels on backgroundPanel with positions
		backgroundPanel.add(leftPanel, BorderLayout.WEST);
		backgroundPanel.add(middlePanel,BorderLayout.CENTER);
		backgroundPanel.add(rightPanel, BorderLayout.EAST);

		// Add backgroundPanel on this frame content pane with positions
		this.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
		
		//add MouseLitsener
		backgroundPanel.addMouseListener(new welcomePanelListener());
		
		this.pack();
		
		// make this component visible
		this.setVisible(true);
	}
	
	private class welcomePanelListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			//if the current state is STATE_UNASSIGNED, then set the model to Initial theModel 
			//System.out.println(theModel.getCurrentState());
			if(theModel.getCurrentState() == -1) {
				theModel.setState(Model.PICKUP_TIME_STATE);
				
				// test on method initTimeSelectionPanel
				initTimeSelectionPanel();
			}
			
			
			//System.out.println(e.getSource());
			//System.out.println("Click anywhere to start!");
		}
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
		
		if(theModel.getCurrentState() == theModel.PICKUP_TIME_STATE) {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("test");
		//System.out.println(e.getActionCommand());
	}
	
	/**
	 * Build the initial question page, which is the next page of welcome page.
	 */
	public void initTimeSelectionPanel() {
		// Remove components from last page
		contentPanel.removeAll();
		
		// Set layout for those panels that will contain other components.
		contentPanel.setLayout(new GridLayout(9, 3));
		pageIndexPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// Set all panels' be not opaque
		//leftPanel.setBackground(Color.WHITE);
		//middlePanel.setBackground(Color.WHITE);
		//rightPanel.setBackground(Color.WHITE);
		//titlePanel.setBackground(Color.WHITE);
		//contentPanel.setBackground(Color.GRAY);
		//pageIndexPanel.setBackground(Color.PINK);
		//storeInfoPanel.setBackground(Color.PINK);
		//this.getContentPane().setBackground(Color.WHITE);
		/*titlePanel.setOpaque(true);
		contentPanel.setOpaque(true);
		pageIndexPanel.setOpaque(true);*/
		
		// Set panels' preferred sizes to make GUI looks nice.
		titlePanel.setPreferredSize(new Dimension(580, 120));
		contentPanel.setPreferredSize(new Dimension(580, 50));
		pageIndexPanel.setPreferredSize(new Dimension(580, 80));
		
		// Part 1: titlePanel
		// Modify titleLabel component
		titleLabel.setFont(new Font(AppViewController.GILL_SANS_MT_FONT, Font.BOLD, 48));
		titleLabel.setForeground(new Color(AppViewController.PAGE_TITLE_LABEL_COLOR));
		// Yi: more work needed here, html text should be stored in Model.java
		System.out.println(theModel.getCurrentState());
		titleLabel.setText(theModel.getPageTitle());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(false);
		
		// Add labels on panels with position.
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		// Part 2: contentPanel
		
		// Part 3: pageIndexpanel
		// Initialize last and next buttons
		lastPageButton = new JButton();
		nextPageButton = new JButton();
		BufferedImage lastPageImg = null;
		BufferedImage nextPageImg = null;
		try {
			lastPageImg = ImageIO.read(new File(AppViewController.LAST_PAGE_BUTTON_IMAGE_PATH));
			nextPageImg = ImageIO.read(new File(AppViewController.NEXT_PAGE_BUTTON_IMAGE_PATH));
		} catch (Exception ex) {
			output.println(ex);
		}
		lastPageButton.setIcon(new ImageIcon(lastPageImg));
		nextPageButton.setIcon(new ImageIcon(nextPageImg));
		lastPageButton.setPreferredSize(new Dimension(lastPageImg.getWidth(), lastPageImg.getHeight()));
		nextPageButton.setPreferredSize(new Dimension(nextPageImg.getWidth(), nextPageImg.getHeight()));
		lastPageButton.setBorderPainted(false);
		nextPageButton.setBorderPainted(false);
		lastPageButton.setOpaque(false);
		nextPageButton.setOpaque(false);
		
		// Initialize pageIndexLabel
		pageIndexLabel = new JLabel();
		BufferedImage pageIndexImg = null;
		try {
			pageIndexImg = ImageIO.read(new File(AppViewController.PAGE_INDEX_1_IMAGE));
		} catch (Exception ex) {
			output.println(ex);
		}
		pageIndexLabel.setIcon(new ImageIcon(pageIndexImg));
		pageIndexLabel.setOpaque(false);
		
		// Add buttons and label on panels with position.
		pageIndexPanel.add(lastPageButton);
		pageIndexPanel.add(pageIndexLabel);
		pageIndexPanel.add(nextPageButton);
		
		
		// Repaint content pane for this frame
		this.getContentPane().repaint();
	}
	
	/**
	 * Build summary page, which is the next page of the last question.
	 */
	public void summaryQuestionPanel() {
		this.getContentPane().repaint();
	}

}
