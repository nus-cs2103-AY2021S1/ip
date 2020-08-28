package alice.command;

import java.util.List;

import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents the command to get the list of commands that ALICE understands.
 */
public class HelpCommand extends Command {
    protected static final List<String> NAMES = List.of("help");
    protected static final String DESCRIPTION = "Gets the list of commands";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    /**
     * Checks if the command word triggers the <code>HelpCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>HelpCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) {
        String commandFormat = "    %-35s | %s\n";

        StringBuilder output = new StringBuilder("These are the commands in my dictionary:\n");
        output.append(String.format(commandFormat, ListCommand.USE_CASE, ListCommand.DESCRIPTION));
        output.append(String.format(commandFormat, FindCommand.USE_CASE, FindCommand.DESCRIPTION));
        output.append(String.format(commandFormat, ClearCommand.USE_CASE, ClearCommand.DESCRIPTION));
        output.append(String.format(commandFormat, DoneCommand.USE_CASE, DoneCommand.DESCRIPTION));
        output.append(String.format(commandFormat, DeleteCommand.USE_CASE, DeleteCommand.DESCRIPTION));
        output.append(String.format(commandFormat, TodoCommand.USE_CASE, TodoCommand.DESCRIPTION));
        output.append(String.format(commandFormat, DeadlineCommand.USE_CASE, DeadlineCommand.DESCRIPTION));
        output.append(String.format(commandFormat, EventCommand.USE_CASE, EventCommand.DESCRIPTION));
        output.append(String.format(commandFormat, USE_CASE, DESCRIPTION));
        output.append(String.format(commandFormat, ByeCommand.USE_CASE, ByeCommand.DESCRIPTION));

        // remove last newline char
        output.setLength(output.length() - 1);
        ui.displayOutput(output.toString());
    }
}
