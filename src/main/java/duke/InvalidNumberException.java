package duke;

public class InvalidNumberException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String errorMessage = "OOPS!!! It seems the number you entered is invalid!";

    public InvalidNumberException() {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}