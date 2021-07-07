package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Represents a command for finding tasks with a designated substring.
 */
public class FindCommand extends Command {
    /**
     * Creates a find command.
     * @param input the input command
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the find command and prints the list of tasks where the given substring can be found
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string description of the filtered table and the number of elements in the table
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        assert input.startsWith(Parser.FIND_COMMAND_PREFIX) : "Find command does not start with 'find '";
        String toFind = input.substring(Parser.FIND_COMMAND_PREFIX.length());
        return ui.printList(list, t -> t.finds(toFind), String.format(Ui.FIND_LIST_NOTE_FORMAT, toFind));
    }

    /**
     * Checks whether the given command equals this one.
     * @param obj the object to compare
     * @return true if obj is a FindCommand and command input is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindCommand) {
            return input.equals(((FindCommand) obj).input);
        } else {
            return false;
        }
    }
}
