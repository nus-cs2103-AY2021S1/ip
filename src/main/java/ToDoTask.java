public class ToDoTask extends Task {

    public ToDoTask(String summary) {
        super(summary);
        this.taskType = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
