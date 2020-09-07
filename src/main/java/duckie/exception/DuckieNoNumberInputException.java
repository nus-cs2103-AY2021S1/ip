package duckie.exception;

/**
 * DuckieNoNumberInputException is thrown when there is no number indicated after
 * the first word to state which task the user is targeting at.
 */
public class DuckieNoNumberInputException extends DuckieException {
    /**
     * Instantiates DuckieNoNumberInputException.
     */
    public DuckieNoNumberInputException() {
        super("You have to input a number after the first word!");
    }

    /**
     * Instantiates DuckieNoNumberInputException with additional message.
     * @param message Additional message to the user
     */
    public DuckieNoNumberInputException(String message) {
        super("You have to input a number after the first word! " + message);
    }
}
