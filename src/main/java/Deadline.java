public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws WrongFormatException {
        super(description, "[D]", "deadline");
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}