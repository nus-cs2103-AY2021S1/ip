package duke.command;

import duke.*;
import duke.task.TaskList;

/**
 * Represents command that is specific to the get command.
 */
public class GetCommand extends Command {

    private String input;

    public GetCommand(String input) {
        this.exit = false;
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
        ui.findTask(Parser.parseDate(input.substring(9)), taskList.getList());
    }

}