public class Event extends Task {

    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public Task setDone() {
        Task doneTask = new Event(this.desc, this.at);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
