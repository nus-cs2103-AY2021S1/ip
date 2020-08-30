package duke.Command;

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
     * @param text index of task to delete.
     * @param taskList current list of tasks.
     */
    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.deleteTask(index);
    }
}
