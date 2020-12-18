package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class ByeCommand extends Command {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon";

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to print an exiting message.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return BYE_MESSAGE;
    }

    /**
     * Overrides isExit in {@link Command}.
     *
     * @return true to indicate an exiting instruction.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
