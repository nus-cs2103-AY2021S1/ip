package duke;

public class Todo extends Task  {
    public Todo(String taskname, boolean status) {
        super(taskname, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
