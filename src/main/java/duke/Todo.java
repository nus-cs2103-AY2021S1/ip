package duke;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public Task setDone() {
        Task doneTask = new Todo(this.desc);
        doneTask.isDone = true;
        return doneTask;
    }

    public String formatTask() {
        return ("T" + " | " + (isDone ? "V" : "X") + " | " + desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
