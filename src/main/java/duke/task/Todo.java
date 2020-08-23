package duke.task;

public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    @Override
    public String returnStringForm() {
        return "[T]" + super.returnStringForm();
    }
}
