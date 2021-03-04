package olivia.util;

/**
 * OliviaException class that represents Exceptions specific to Olivia.
 */

public class OliviaException extends Exception {

    /**
     * Constructor that creates an OliviaException.
     * @param error a String containing the error message.
     */

    public OliviaException(String error) {
        super(error);
    }
}
