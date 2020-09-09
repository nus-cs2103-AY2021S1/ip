package duke.command;

import duke.TaskList;

/**
 * Represents command to delete a specific task.
 */
public class DeleteCommand extends Command {

    public DeleteCommand() {
        this.commandText = "delete";
    }

    /**
     * Deletes the particular task.
     *
     * @param text     index of task to delete.
     * @param taskList current list of tasks.
     */
    @Override
    public String execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        return taskList.deleteTask(index);
    }
}
