package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * ListCommand is Command to list all the Task that the user have stored in the related TaskList.
 */
public class ListCommand extends Command {

    protected TaskList tasks;

    /**
     * Lists all the task in the related TaskList.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
    }

    public String getReply() {
        String reply = " These are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            reply = reply + "\n" + " " + (i + 1) + "." + t.toString();
        }
        return reply;
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
