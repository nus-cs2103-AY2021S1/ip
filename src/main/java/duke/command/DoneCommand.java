package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Represents a command for marking a task as done.
 */
public class DoneCommand extends Command {
    /**
     * Creates a command for marking tasks as done.
     * @param input the input command classified as DoneCommand, starting with "done "
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Executes the command, prints the result on ui and re-writes the source data file.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string that is to be printed on ui if the marking is successful
     * @throws InvalidCommandException if the input index for marking as done is invalid
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        assert input.startsWith("done ") : "Done command does not start with 'done '";
        int n = Parser.getDoneTaskIndex(input, list.size()) - 1;
        list.get(n).markAsDone();
        storage.reWrite(list);
        String str = String.format(Ui.DONE_TASK_OUTPUT_FORMAT, list.get(n));
        ui.output(str);
        return str;
    }

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a DoneCommand and it has the same input as this one
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DoneCommand) {
            return input.equals(((DoneCommand) obj).input);
        } else {
            return false;
        }
    }
}
