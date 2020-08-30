package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to print a help window to aid the user.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printHelpWindow(obtainCommands());
    }

    private String[] obtainCommands() {
        String[] result = new String[CommandType.values().length];
        int i = 0;

        for (CommandType command : CommandType.values()) {
            result[i++] = command.toString();
        }

        return result;
    }

}
