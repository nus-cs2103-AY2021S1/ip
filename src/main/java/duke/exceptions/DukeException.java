package duke.exceptions;

/**
 * Basic exception class specially written for the Duke Chatbot.
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     * @param message Error message
     */

    public DukeException(String message){
        super(message);
    }

    /**
     * Default toString() method.
     *
     * @return String representing the error message.
     */

    @Override
    public String toString() {
        return "---------------------------------------------------"
                + "\nFAILURE: " + super.getMessage();
    }
}