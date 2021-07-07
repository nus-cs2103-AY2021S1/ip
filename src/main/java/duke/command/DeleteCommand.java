package duke.command;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

/**
 * Represents a command for deleting a task.
 */
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
        assert input.startsWith("delete ") : "Delete command does not start with 'delete '";
        int count = list.size();
        int m = Parser.getDeleteTaskIndex(input, count) - 1;
        Task toDelete = getTaskToDelete(list, storage, m);
        return getUiOutput(ui, list, count, toDelete);
    }

    private String getUiOutput(Ui ui, TaskList list, int count, Task toDelete) {
        String tasks = Parser.getTaskPlural(count - 1);
        String str = String.format(Ui.DELETE_TASK_OUTPUT_FORMAT, toDelete.toString(), list.size(), tasks);
        ui.output(str);
        return str;
    }

    private Task getTaskToDelete(TaskList list, Storage storage, int m) throws InvalidCommandException {
        Task toDelete = list.get(m);
        list.remove(m);
        storage.reWrite(list);
        return toDelete;
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
