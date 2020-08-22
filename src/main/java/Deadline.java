public class Deadline extends Task {
    //@@author Damith C. Rajapakse
    //Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    public String formatStyling() {
        return String.format("deadline,%s%s", by, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    //@@author
}
