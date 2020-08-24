package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String showTask() {
        return String.format("[%s]%s", this.getType(), super.showTask());
    }

    @Override
    public String getType() {
        return "T";
    }
}
