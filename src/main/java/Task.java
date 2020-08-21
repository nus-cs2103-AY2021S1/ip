public class Task {

    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task setDone() {
        Task doneTask = new Task(this.desc);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "V" : "X") + "] " + desc);
    }
}
