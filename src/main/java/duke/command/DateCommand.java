package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command that is specific to the get command.
 */
public class DateCommand extends Command {

    private String input;

    /**
     * Creates GetCommand object.
     * @param input input of user.
     */
    public DateCommand(String input) {
        this.isExit = false;
        this.input = input;
    }

    /**
     * Executes get command of user.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        assert taskList != null : "TaskList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";

        ui.setFindTaskByDateMessage(Parser.parseDate(input.substring(9)), taskList.getList());
    }

}
