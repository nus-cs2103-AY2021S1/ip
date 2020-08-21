public class Deadline extends Task {

    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = by;
    }

    @Override
    public Task setDone() {
        Task doneTask = new Deadline(this.desc, this.by);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String formatTask() {
        return ("D" + " | " + (isDone ? "V" : "X") + " | " + desc + " | " + this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
