public class Event extends Task {

    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    public Event(String desc, boolean isDone, String at) {
        super(desc, isDone);
        this.at = at;
    }

    @Override
    public Task setDone() {
        Task doneTask = new Event(this.desc, this.at);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String formatTask() {
        return ("E" + " | " + (isDone ? "V" : "X") + " | " + desc + " | " + this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
