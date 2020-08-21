public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public Task setDone() {
        Task doneTask = new Todo(this.desc);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
