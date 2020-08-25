public class Deadline extends Task {
    private static final String DELIMITER = "by";
    private String dateString = "";
    
    public Deadline(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }
    
    // example: deadline return book /by Sunday
    public static Deadline createDeadline(String[] parsedOutput) {
        String description = parsedOutput[1];
        String dateString = parsedOutput[2];
        return new Deadline(description, dateString);
    }
    
    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (" + DELIMITER + ":" + this.dateString + ")";
    }
}
