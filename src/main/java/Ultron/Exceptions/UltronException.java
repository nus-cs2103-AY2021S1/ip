package ultron.exceptions;

public class UltronException extends Exception {

    /**
     * Ultron Exception for Ultron class.
     * @param inputString   Input the user has provided
     * @param errorType     The error type
     */
    public UltronException(final String inputString,
                           final ExceptionType errorType) {

        //Call the superclass method
        super(errorType.getMessage(inputString));
    }

    /**
     * Ultron Exception for Ultron class.
     * @param inputString       Input the user has provided
     * @param inputArguments    The arguments provided
     * @param errorType         The error type
     */
    public UltronException(final String inputString,
                           final String inputArguments,
                           final ExceptionType errorType) {

        //Call the superclass method
        super(errorType.getMessage(inputString, inputArguments));
    }
}
