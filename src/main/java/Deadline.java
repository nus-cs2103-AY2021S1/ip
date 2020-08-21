public class Deadline extends Task {

    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public Task setDone() {
        Task doneTask = new Deadline(this.desc, this.by);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
