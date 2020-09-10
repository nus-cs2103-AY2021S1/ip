package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * ListCommand is Command to list all the Task that the user have stored in the related TaskList.
 */
public class ListCommand extends Command {
    /** TaskList that is related to this command **/
    private TaskList tasks;

    /**
     * Lists all the task in the related TaskList.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
    }

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    @Override
    public String getReply() {
        String reply = " These are the tasks in your list:";
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task t = tasks.get(i);
            reply += "\n" + " " + i + "." + t.toString();
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
