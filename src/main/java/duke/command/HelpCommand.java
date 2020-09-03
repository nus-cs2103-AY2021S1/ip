package duke.command;

import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to print a help window to aid the user.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage) {
        return new CommandResult(output.printHelpWindow(obtainCommands()));
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
