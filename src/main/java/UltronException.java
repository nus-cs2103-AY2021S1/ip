public class UltronException extends Exception {

    public UltronException(final String inputString,
                           final  ExceptionType errorType) {

        //Call the superclass method
        super(errorType.getMessage(inputString));
    }

    public UltronException(final String inputString,
                           final String inputArguments,
                           final ExceptionType errorType) {

        //Call the superclass method
        super(errorType.getMessage(inputString, inputArguments));
    }
}
