public class Todo extends Task{

    protected Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        return "[T][" + done + "] " + this.task;
    }
}
