package ultron.exceptions;

public enum ExceptionType {
    TOO_MUCH_ARGUMENTS("Too much arguments supplied for %s"),
    NO_ARGUMENTS_SUPPLIED("No arguments supplied for %s"),
    INVALID_ARGUMENT("Invalid value of %s with '%s'"),
    INVALID_NUMBER("'%s' is not a valid number"),
    INVALID_COMMAND("Invalid command '%s'"),
    IO_EXCEPTION("How do I not have permission for %s"),
    DIRECTORY_NOT_CREATED("Unable to create directory %s");

    //Store the error message
    private final String errorMessage;

    ExceptionType(final String errorMessage) {

        //Store the error message
        this.errorMessage = errorMessage;
    }

    public String getMessage(final String inputString,
                             final String inputArguments) {

        //Return the string formatted error message
        return String.format(this.errorMessage, inputString, inputArguments);
    }

    public String getMessage(final String inputString) {

        //Return the formatted error message
        return String.format(this.errorMessage, inputString);

    }
}
