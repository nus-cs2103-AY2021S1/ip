public class UltronException extends Exception{

    public UltronException(String inputString, ExceptionType errorType){

        //Call the superclass method
        super(errorType.getMessage(inputString));
    }

    public UltronException(String inputString, String inputArguments, ExceptionType errorType){

        //Call the superclass method
        super(errorType.getMessage(inputString, inputArguments));
    }
}
