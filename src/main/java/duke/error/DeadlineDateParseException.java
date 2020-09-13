package duke.error;

public class DeadlineDateParseException extends Exception {
    public DeadlineDateParseException(String date) {
        super(String.format("    Please the correct format, %s is\n not a valid date", date));
    }
}
