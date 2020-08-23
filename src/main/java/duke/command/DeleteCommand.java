package duke.command;

/**
 * Represents a command that asks the app to delete a task from the list.
 */

import duke.task.Task;
import duke.component.*;

public class DeleteCommand extends Command {
    /**
     * Creates a command for deleting tasks.
     * @param input The input command classified as DeleteCommand, starting with "delete ".
     */
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int count = list.size();
        int m = Parser.isValidDelete(input, count) - 1;
        Task toDelete = list.get(m);
        list.remove(toDelete);
        storage.reWrite(list);
        String temp = count <= 2 ? " task" : " tasks";
        String str = "Noted. I've removed this task:\n\t    " + toDelete +
                "\n\t  Now you have " + list.size() + temp;
        ui.output(str);
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            return input.equals(((DeleteCommand) obj).input);
        } else {
            return false;
        }
    }
}
