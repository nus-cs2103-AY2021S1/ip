package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Represents a command for exiting the app.
 */
public class ByeCommand extends Command {
    /**
     * Creates a command for exiting.
     * @param input the input command "bye"
     */
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * Tells whether this command is aiming for exiting.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    private String printEntireList(Ui ui, TaskList list) {
        return ui.printList(list, t -> true, "");
    }

    /**
     * Executes the command, prints the current list on ui.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string "bye"
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        return printEntireList(ui, list);
    }

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a ByeCommand
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
