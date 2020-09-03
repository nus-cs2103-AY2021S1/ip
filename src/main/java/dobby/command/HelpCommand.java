package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class HelpCommand implements Command {

    protected static final String USAGE = "help";
    protected static final String ALL_COMMANDS = "You can use the following commands in this chat bot:"
            + "\n  " + TodoCommand.USAGE
            + "\n  " + DeadlineCommand.USAGE
            + "\n  " + EventCommand.USAGE
            + "\n  " + ListCommand.USAGE
            + "\n  " + DoneCommand.USAGE
            + "\n  " + DeleteCommand.USAGE
            + "\n  " + ScheduledCommand.USAGE
            + "\n  " + FindCommand.USAGE
            + "\n  " + FindtypeCommand.USAGE
            + "\n  " + HelpCommand.USAGE
            + "\n  " + ByeCommand.USAGE;

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        return ALL_COMMANDS;
    }

    public String getAllCommands() {
        return ALL_COMMANDS;
    }
}
