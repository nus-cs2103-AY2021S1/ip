package ultron.exceptions;

public enum ExceptionType {
    /**
     * ExceptionType when too much arguments are supplied.
     */
    TOO_MUCH_ARGUMENTS("Clearly there is too much arguments for %s"),

    /**
     * ExceptionType when there are no arguments supplied.
     */
    NO_ARGUMENTS_SUPPLIED("%s has arguments you know"),

    /**
     * ExceptionType when arguments are invalid.
     */
    INVALID_ARGUMENT("Invalid value of %s with '%s'"),

    /**
     * ExceptionType when the input is not a valid number.
     */
    INVALID_NUMBER("'%s' is not a valid number. Do you even know what is a number?"),

    /**
     * ExceptionType when there is no such command.
     */
    INVALID_COMMAND("What are you even saying? There is no '%s'"),

    /**
     * ExceptionType when there are IO errors.
     */
    IO_EXCEPTION("Blasphemy! How do I not have permission for %s"),

    /**
     * ExceptionType when Directory cannot be created.
     */
    DIRECTORY_NOT_CREATED("I've accidentally destroyed the folder that was suppose to be made %s");

    /**
     * Stores the error message for the enum.
     */
    private final String errorMessage;

    /**
     * Different types of Exceptions.
     *
     * @param errorMessage Error Message to be shown
     */
    ExceptionType(final String errorMessage) {

        //Store the error message
        this.errorMessage = errorMessage;
    }

    /**
     * Get the message of the ExceptionType.
     *
     * @param inputString    Input string that the user have given
     * @param inputArguments Input arguments the user have given
     * @return The message to be shown
     */
    public String getMessage(final String inputString,
                             final String inputArguments) {

        //Return the string formatted error message
        return String.format(this.errorMessage, inputString, inputArguments);
    }

    /**
     * Get the message of the ExceptionType.
     *
     * @param inputString Input string that the user have given
     * @return The message to be shown
     */
    public String getMessage(final String inputString) {

        //Return the formatted error message
        return String.format(this.errorMessage, inputString);

    }
}
