package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

/**
 * This command handles the sorting of the task list via appropriate
 * parameters given by the user.
 */
public class SortCommand implements Command {

    private final String sortBy;

    /**
     * Constructs the sort command which will sort by the input parameter.
     * @param sortBy the parameter used to sort the task list
     * @throws DukeException when the parameter is not supported or incorrect.
     */
    public SortCommand(String sortBy) throws DukeException {
        assert List.of("name", "type", "datetime").contains(sortBy)
                : "sortBy should only be name, type, or datetime.";
        if (!List.of("name", "type", "datetime").contains(sortBy)) {
            throw new DukeException("Invalid sorting parameter!");
        }
        this.sortBy = sortBy;
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     * @throws DukeException if command execution fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getList().isEmpty()) {
            String msg = ui.getEmptyListSortingMessage();
            ui.sendMessage(msg);
            return msg;
        }
        tasks.sort(sortBy);
        storage.update(tasks.getList());
        String msg = ui.getSuccessSortingMessage();
        ui.sendMessage(msg);
        return msg;
    }
}
