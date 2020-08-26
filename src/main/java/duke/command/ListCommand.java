package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * ListCommand is Command to list all the Task that the user have stored in the related TaskList.
 */
public class ListCommand extends Command {

    /**
     * Lists all the task in the related TaskList.
     *
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        System.out.println(" These are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + t.toString());
        }
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
