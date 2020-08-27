package duke;

public class EmptyDeadLineException extends Exception {

    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! The description of a deadline cannot be empty.";

    /**
     * EmptyDeadLineException constructor
     */
    public EmptyDeadLineException() {
        super(errorMessage);
    }

    /**
     * Returns error message when occurs empty deadline exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {
        return errorMessage;
    }

}