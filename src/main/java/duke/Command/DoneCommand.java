package duke.Command;

import duke.TaskList;

/**
 * Represents command to mark a specific task complete.
 */
public class DoneCommand extends Command {

    public DoneCommand() {
        this.commandText = "done";
    }
    /**
     * Marks the particular task complete.
     * @param text index of task to mark complete.
     * @param taskList current list of tasks.
     */
    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.completeTask(index);
    }
}
