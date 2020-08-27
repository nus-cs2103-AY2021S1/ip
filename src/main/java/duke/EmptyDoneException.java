package duke;

public class EmptyDoneException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! The description of a done cannot be empty. ";

    public EmptyDoneException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}