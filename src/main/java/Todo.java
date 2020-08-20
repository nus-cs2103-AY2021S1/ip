public class Todo extends Task{

    protected Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        return "[T][" + done + "] " + this.task;
    }
}
