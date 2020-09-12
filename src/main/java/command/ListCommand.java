package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Class to initiate the List Command
 */
public class ListCommand extends Command {

    /**
     * Constructor for the list command
     */
    public ListCommand() {
        super(Command.CommandType.List);
    }


    /**
     * Runs the command to display the user's Tasks
     *
     * @param taskList ArrayList of Tasks Objects.
     * @param ui       Object of the Ui class.
     * @param storage  Object of the Storage class.
     * @throws DukeException Exception that occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.displayTaskList(taskList);
    }

}
