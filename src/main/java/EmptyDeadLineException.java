public class EmptyDeadLineException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! The description of a deadline cannot be empty.";

    public EmptyDeadLineException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}