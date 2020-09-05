package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to add new Todo.
 */
public class AddTodoCommand extends Command {

    /** Description of Todo to be added */
    private final String description;

    /**
     * Constructs a <code>AddTodoCommand</code> object.
     *
     * @param description Description of Todo to be added.
     */
    public AddTodoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Adds a Todo to the TaskList and notify the user if successful.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        String responseMessage = "Got it. I've added this todo: \n\t   "
                + task + "\n\t "
                + "Now you have "
                + tasks.getNumberOfTaskDescription()
                + " in the list.";
        boolean shouldExit = getIsExit();
        return new CommandResponse(responseMessage, shouldExit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddTodoCommand) {
            AddTodoCommand c = (AddTodoCommand) obj;
            return c.description.equals(this.description) && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}
