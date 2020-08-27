package duke;

public class EmptyDeleteException extends Exception {
    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! The description of a delete cannot be empty.";

    /**
     * EmptyDeleteException constructor.
     */
    public EmptyDeleteException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}