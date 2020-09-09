package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command that is specific to the delete command.
 */
public class DeleteCommand extends Command {

    private String input;

    /**
     * Creates DeleteCommand object.
     * @param input input of user.
     */
    public DeleteCommand(String input) {
        this.isExit = false;
        this.input = input;
    }

    /**
     * Executes delete command based on user input.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If invalid input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        assert taskList != null : "TaskList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        if (Parser.isValidIndex(input, taskList.getListSize())) {
            int index = Parser.getIndex(input);
            ui.setDeletedMessage(taskList.getList().get(index), taskList.getListSize());
            taskList.deleteTask(index);
            storage.saveListToFile(taskList.getList());
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }
}
