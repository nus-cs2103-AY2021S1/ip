package duke.task;

public class TodoStub extends Task {

    TodoStub(String description) {
        super(description);
    }

    public static Task createTask() {
        return new TodoStub("buy bread");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
