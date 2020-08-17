package duke;

public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    @Override
    public String showTask() {
        return String.format("[T]%s", super.showTask());
    }
}
