package models;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

/**
 * This class encapsulates the data model of a data collection protocol. The
 * model has four possible states: init state (has an associated string
 * containing initial instructions), question state (has an associated questions
 * and choices that users can select), color question state (has an associated
 * questions and choices with color buttons), and end state (shows collected
 * data from last two states). This class provides services for asking multiple
 * choice questions about color.
 * 
 * This Model is an Observable.
 * 
 * @author Tong Wu - 212531380
 * @author Yiwei Wang - 213267026
 *
 */
public class Model extends Observable {

	public static final int STATE_UNASSIGNED = -1;
	public static final int PICKUP_TIME_STATE = 0;
	public static final int CHOOSE_CAKE_STATE = 1;
	public static final int YOUR_INFORMATION_STATE = 2;
	public static final int CONFIRMATION_STATE = 3;
	public static final int ORDER_NUM_END_STATE = 4;

	private PrintStream output = System.out;

	private int currentState;
	
	private String windowTitle = "Uncle Tetsu Reservation";
	private String subtitle = "type anywhere to start";
	private String storeInfo = "<html><p style=\"text-align:center\">598 Bay St, Toronto<br>416-591-0555<br>Monday 10am-11pm<br>Tues-Sun 8am-11pm</p></html>";
	private String appTitle = "<html><p style=\"text-align:center\">Uncle Tetsu<br>Reservation</p></html>";
	private String[] pageTitle = new String[] {"PICKUP TIME", "CHOOSE CAKE", "YOUR INFORMATION", "CONFIRMATION"};
	//private String[] pageTitle = {"PICKUP TIME", "CHOOSE CAKE", "YOUR INFORMATION", "CONFIRMATION"};
	
	/**
	 * Create an instance of this model. The question list for color game will
	 * be initialized here.
	 */
	public Model() {
		this.setState(Model.STATE_UNASSIGNED);
	}

	/**
	 * Set change, and notify all observers with given parameter
	 * 
	 * @param o
	 *            changes that should be sent to every observer
	 */
	private void modelNotify(Object o) {
		// print something to the console, for the sake of tracing program
		// control flow
		// output.println("Change happened to the data model");
		// indicate that the state of this Observable has changed
		setChanged();
		// System.out.println(hasChanged());
		// notify the observers that the state has changed
		notifyObservers(o);
	}

	/**
	 * Set state of this model, it will notify all its observers
	 * 
	 * @param modelState
	 *            the state for the model. Passed parameter must be one of the
	 *            class fields.
	 * 
	 *            Mutate the current state of this model.
	 */
	public void setState(int modelState) {
		currentState = modelState;
		modelNotify(currentState);
	}

	/**
	 * Return the current state of this model
	 * 
	 * @return the current state of this model (value will be one of the class
	 *         fields).
	 */
	public int getCurrentState() {
		return currentState;
	}
	
	/**
	 * Return the title on the window
	 * 
	 * @return the title
	 */
	public String getWindowTitle() {
		return windowTitle;
	}
	
	/**
	 * Return the Touch to start message
	 * 
	 * @return the Touch to start message
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Return the welcome message in initial page
	 * 
	 * @return the welcome message
	 */
	public String getAppTitle() {
		return appTitle;
	}
	
	/**
	 * Return the Address and open hour
	 * 
	 * @return the Address and open hour
	 */
	public String getStoreInfo() {
		return storeInfo;
	}
	
	/**
	 * Return the current page title
	 * 
	 * @return the current page title
	 */
	
	public String getPageTitle() {
		String curPageTilte = null;
		switch(this.getCurrentState()) {
		case 0:
			curPageTilte = pageTitle[0];
			break;
		case 1:
			curPageTilte = pageTitle[1];
			break;
		case 2:
			curPageTilte = pageTitle[2];
			break;
		case 3:
			curPageTilte = pageTitle[3];
			break;
		}
		return curPageTilte;
	}
	
	/**
	 * Return the initialization message
	 * 
	 * @return the initialization message associated with the INIT_STATE of this
	 *         model.
	 *
	public String getInitMsg() {
		return initMsg;
	}
	*/
	
	/**
	 * Return the finish message
	 * 
	 * @return the finish message associated with the END_STATE of this model.
	 *
	public String getEndMsg() {
		return endMsg;
	}
	*/
}
