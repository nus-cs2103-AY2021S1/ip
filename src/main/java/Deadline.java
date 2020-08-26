public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markAsDone() {
        Deadline doneDeadline = new Deadline(this.description, this.by, true);
        System.out.println(" ____________________________________________________________\n " +
                "Nice! I've marked this task as done:\n    " +
                doneDeadline.toString() +
                "\n ____________________________________________________________");
        return doneDeadline;
    }

    @Override
    public String toTxtFileFormat() {
        return "D" + super.toTxtFileFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}