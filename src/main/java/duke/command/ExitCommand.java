package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {

    private static String EXIT_MESSAGE = "Goodbye! Hope to see you again soon!";

    /**
     * Prints exit message on the Ui.
     *
     * @param taskList The TaskList used by Duke.
     * @param storage  The Storage used by Duke.
     * @return CommandResult object for ui
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        assert taskList != null && storage != null;
        CommandResult commandResult = new CommandResult(EXIT_MESSAGE);
        commandResult.setIsExit();
        return commandResult;
    }

    /**
     * Returns true since this is a ExitCommand.
     *
     * @return true.
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }

}
