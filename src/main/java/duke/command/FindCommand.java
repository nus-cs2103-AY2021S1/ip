package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

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
     * @param ui
     * @param list
     * @param storage
     * @return the string description of the filtered table and the number of elements in the table
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        String toFind = input.substring(5);
        return ui.printList(list, t -> t.finds(toFind), "containing '" + toFind + "' ");
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
