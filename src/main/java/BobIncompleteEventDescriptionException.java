package main.java;

/**
 * An exception to be thrown when an incomplete event description is provided.
 */
public class BobIncompleteEventDescriptionException extends BobException {

    /**
     * Returns a message that indicates the event description provided is incomplete, and the correct format.
     *
     * @return a message that indicates the event description provided is incomplete, and the correct format.
     */
    public String getMessage(){
        return "The description for this event is incomplete. Please remember to include a brief description alongside the period of this event.\n"
                + "Here's the format: event [brief description] /at [period]";
    }
}
