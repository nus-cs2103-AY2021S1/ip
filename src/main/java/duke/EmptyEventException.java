package duke;

public class EmptyEventException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String errorMessage = "OOPS!!! The description of a event cannot be empty.";

    public EmptyEventException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}
