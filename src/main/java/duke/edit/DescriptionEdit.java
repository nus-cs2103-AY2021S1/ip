package duke.edit;

import duke.task.Task;

/**
 * An Edit which changes the description of a Task. This Edit will not throw an EditingException.
 */
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
