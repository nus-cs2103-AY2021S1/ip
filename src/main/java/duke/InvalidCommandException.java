package duke;

public class InvalidCommandException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static String errorMessage = "OOPS!!! It seems the command you entered is invalid!";

    public InvalidCommandException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}