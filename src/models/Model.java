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
	public static final int INIT_STATE = 0;
	//public static final int QUESTION_STATE = 1;
	//public static final int COLOR_QUESTION_STATE = 2;
	//public static final int END_STATE = 3;

	private PrintStream output = System.out;

	private int currentState;
	
	private String welcomeMsg = "<b>Uncle Tetsu Reservation</b><br><br>Touch anywhere to start";
	//private String initMsg = "Choose the color of provided items";
	//private String endMsg = "Congras! You just completed!";
	
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
	 * Return the welcome message in initial page
	 * 
	 * @return the welcome message
	 */
	public String getWelcomeMsg() {
		return welcomeMsg;
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
