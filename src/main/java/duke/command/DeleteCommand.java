package duke.command;


import duke.Storage;
import duke.task.Task;
import duke.tool.TaskList;

/**
 * Represents a command to delete element with certain index in the task list.
 */
public class DeleteCommand implements Command {

    /** Index of the target task to be deleted */
    private final int targetIndex;
    private Task deletedTask;
    private int currentListSize;

    /**
     * Creates a delete command to delete specific task in the list.
     *
     * @param index Index of task that will be deleted.
     */
    public DeleteCommand(int index) {
        this.targetIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        deletedTask = tasks.delete(targetIndex);
        storage.save(tasks);
        currentListSize = tasks.getSize();
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return "Noted. I've removed this task: " + "\n\t\t" +
                deletedTask.toString()
                + String.format("\nNow you have %d tasks in the list.\n", currentListSize);
    }

}
