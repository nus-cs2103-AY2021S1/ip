package duke.command;

import duke.task.Task;
import duke.component.*;

public class DeleteCommand extends Command {
    /**
     * Creates a command for deleting tasks.
     * @param input the input command classified as DeleteCommand, starting with "delete "
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the command, prints the result on ui and re-writes the source data file.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string that is to be printed on ui if the deleting is successful
     * @throws InvalidCommandException if the input index for deleting is invalid
     */
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

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a DeleteCommand and it has the same input as this one
     */
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
