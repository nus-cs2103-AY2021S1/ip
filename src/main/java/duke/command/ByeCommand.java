package duke.command;

import duke.component.*;

public class ByeCommand extends Command {
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command, prints the current list on ui.
     * @param ui The user interface object that is currently running.
     * @param list The current list of tasks.
     * @param storage The storage-writing object that is currently running.
     * @return The string "bye".
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
        return "bye";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
