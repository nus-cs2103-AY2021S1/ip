package duke.error;

public class DeadlineDateParseException extends Exception {
    public DeadlineDateParseException(String date) {
        super(String.format("    Please the correct format, %s is not a valid date", date));
    }
}
