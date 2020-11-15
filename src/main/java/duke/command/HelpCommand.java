package duke.command;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Responsible for executing a help command.
 */
public class HelpCommand extends Command {
    /**
     * List of all possible commands.
     */
    private static List<Command> allCommands = Arrays.asList(
            new ByeCommand(),
            new DeadlineCommand("dummy value", new Date()),
            new DeleteCommand(0),
            new DoneCommand(0),
            new EventCommand("dummy value", new Date()),
            new FindCommand("dummy value"),
            new ListCommand(),
            new ToDoCommand("dummy value"),
            new SortCommand()
    );

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        super(true);
    }

    /**
     * Executes a help command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        response.add("Here are all the available commands:");
        for (Command command : allCommands) {
            response.add(command.toString());
        }
        return response.toString();
    }
}
