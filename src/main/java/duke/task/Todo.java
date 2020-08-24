package duke.task;

import duke.task.Task;

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
