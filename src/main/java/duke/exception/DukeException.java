package duke.exception;

public abstract class DukeException extends Exception {
    private final String exceptionType;
    private final String additionalInfo;

    public DukeException(String message, String exceptionType, String additionalInfo) {
        super(message);
        this.exceptionType = exceptionType;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "Exception: " + this.exceptionType +
                "\n\t" + getMessage() +
                "\n\t" + additionalInfo +
                "\n\tSee \"help\" for more";
    }
}