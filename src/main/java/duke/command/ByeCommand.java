package duke.command;

import duke.main.TaskList;

/**
 * ByeCommand is a Command to shut down Duke.
 */
public class ByeCommand extends Command {

    /**
     * Says bye to the user.
     *
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        // Nothing to perform since this is a termination command.
    }

    public String getReply() {
        return " Bye! Hope to see you again in the future!";
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
