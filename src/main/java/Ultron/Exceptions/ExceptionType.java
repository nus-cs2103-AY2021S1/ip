package ultron.exceptions;

public enum ExceptionType {
    /**
     * ExceptionType when too much arguments are supplied.
     */
    TOO_MUCH_ARGUMENTS("Too much arguments supplied for %s"),

    /**
     * ExceptionType when there are no arguments supplied.
     */
    NO_ARGUMENTS_SUPPLIED("No arguments supplied for %s"),

    /**
     * ExceptionType when arguments are invalid.
     */
    INVALID_ARGUMENT("Invalid value of %s with '%s'"),

    /**
     * ExceptionType when the input is not a valid number.
     */
    INVALID_NUMBER("'%s' is not a valid number"),

    /**
     * ExceptionType when there is no such command.
     */
    INVALID_COMMAND("Invalid command '%s'"),

    /**
     * ExceptionType when there are IO errors.
     */
    IO_EXCEPTION("How do I not have permission for %s"),

    /**
     * ExceptionType when Directory cannot be created.
     */
    DIRECTORY_NOT_CREATED("Unable to create directory %s");

    /**
     * Stores the error message for the enum.
     */
    private final String errorMessage;

    /**
     * Different types of Exceptions.
     * @param errorMessage  Error Message to be shown
     */
    ExceptionType(final String errorMessage) {

        //Store the error message
        this.errorMessage = errorMessage;
    }

    /**
     * Get the message of the ExceptionType.
     * @param inputString       Input string that the user have given
     * @param inputArguments    Input arguments the user have given
     * @return  The message to be shown
     */
    public String getMessage(final String inputString,
                             final String inputArguments) {

        //Return the string formatted error message
        return String.format(this.errorMessage, inputString, inputArguments);
    }

    /**
     * Get the message of the ExceptionType.
     * @param inputString   Input string that the user have given
     * @return The message to be shown
     */
    public String getMessage(final String inputString) {

        //Return the formatted error message
        return String.format(this.errorMessage, inputString);

    }
}
