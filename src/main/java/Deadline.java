public class Deadline extends Task{

    protected String by;

    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone,String description,String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String toFileStringFormat() {
        return String.format("D / %d / %s / %s",isDone ? 1 : 0, this.desciption,this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
