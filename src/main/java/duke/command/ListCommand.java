package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Represents a command for listing all existing tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a command for getting the current entire list.
     * @param input the input command "list"
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the command, prints the current list on ui.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string "list"
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        assert input.equals("list") : "List command is not 'list'";
        return ui.printList(list, t -> true, "");
    }

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a ListCommand
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
