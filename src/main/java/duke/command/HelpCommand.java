package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Shows all the valid commands recognized by Duke.\n"
        + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        Ui.printHelp(input);
    }
}
