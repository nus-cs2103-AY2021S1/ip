package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command that is specific to the get command.
 */
public class GetCommand extends Command {

    private String input;

    /**
     * Creates GetCommand object.
     * @param input input of user.
     */
    public GetCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    /**
     * Executes get command of user.
     * @param tasks tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.setFindTaskByDateMessage(Parser.parseDate(input.substring(9)), tasks.getList());
    }

}
