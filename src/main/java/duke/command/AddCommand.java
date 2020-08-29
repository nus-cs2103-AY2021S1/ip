package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tool.TaskList;

/**
 * Represents an command that add task to list.
 * @author Linngy
 * @version 0.1
 */
public class AddCommand implements Command {

    /** Target task that will be added to the list */
    private final Task targetTask;
    private int currentListSize;

    /**
     * Creates a command to add task.
     *
     * @param task Task that will be added to the list.
     */
    public AddCommand(Task task) {
        this.targetTask = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.add(targetTask);
        storage.save(tasks);
        currentListSize = tasks.getSize();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return "Got it. I've added this task: " + "\n\t\t"
                + targetTask.toString() + "\n\t"
                + String.format("Now you have %d tasks in the list.\n", currentListSize);
    }

}
