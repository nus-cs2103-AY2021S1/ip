package duke.command;

import duke.task.Task;

public class DescriptionEdit implements Edit<Task> {
    private String description;

    public DescriptionEdit(String description) {
        this.description = description;
    }

    @Override
    public void apply(Task task) {
        task.setDescription(description);
    }
}
