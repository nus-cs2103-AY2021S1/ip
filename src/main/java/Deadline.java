public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void invalidInput() {
        invalidInput("OOPS!!! The format of the Deadline is wrong.");
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}