package main.java;

/**
 * An exception to be thrown when an incomplete deadline description is provided.
 */
public class BobIncompleteDeadlineDescriptionException extends BobException {

    /**
     * Returns a message that indicates the deadline description provided is incomplete, and the correct format.
     *
     * @return a message that indicates the deadline description provided is incomplete, and the correct format.
     */
    @Override
    public String getMessage() {
        return "The description for this deadline is incomplete. Please remember to include a brief description alongside a due date.\n" +
                "Here's the format: deadline [brief description] /by [due date]";
    }
}
