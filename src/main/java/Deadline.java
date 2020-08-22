public class Deadline extends Task {

   protected String by;

    public Deadline(String description) {
        super(description);
    }

    String[] stringSegments = description.split("/by", 2);

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + stringSegments[0] + " (by: " + stringSegments[1] + ")";
    }
}