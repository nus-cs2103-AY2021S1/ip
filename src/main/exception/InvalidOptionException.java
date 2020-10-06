package main.exception;

/**
 * Thrown to indicate the option selected is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.3
 */
public class InvalidOptionException extends StuffException {

    /**
     * Constructs an InvalidOptionException instance.
     * @param alias the invalid alias from the user input.
     */
    public InvalidOptionException(String alias) {
        super(String.format("Your option of %s does not exist!", alias));
    }
}
