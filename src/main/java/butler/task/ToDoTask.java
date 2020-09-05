package butler.task;

public class ToDoTask extends Task {

    public ToDoTask(String summary) {
        super(summary);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
