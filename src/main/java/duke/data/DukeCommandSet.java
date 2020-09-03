package duke.data;

import java.util.HashMap;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.UnknownCommandException;

/**
 * DukeCommandSet contains all supported commands in Duke, and provides
 * a method to get commands using command names.
 */
public class DukeCommandSet {

    private HashMap<String, Command> commandSet;

    /**
     * Constructs a DukeCommandSet, register all supported commands.
     */
    public DukeCommandSet() {
        commandSet = new HashMap<>();

        registerCommand(new ExitCommand());
        registerCommand(new ListCommand());
        registerCommand(new DoneCommand());
        registerCommand(new TodoCommand());
        registerCommand(new DeadlineCommand());
        registerCommand(new EventCommand());
        registerCommand(new DeleteCommand());
        registerCommand(new FindCommand());
    }

    private void registerCommand(Command command) {
        for (String name : command.getNames()) {
            commandSet.put(name, command);
        }
    }

    /**
     * Gets the command using its name.
     * @param commandName the name of the command
     * @return the command
     * @throws UnknownCommandException thrown when the name does not belong to any commands
     */
    public Command getCommand(String commandName) throws UnknownCommandException {
        if (!commandSet.containsKey(commandName)) {
            String errMessage = "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n";

            throw new UnknownCommandException(errMessage);
        }

        return commandSet.get(commandName);
    }
}
