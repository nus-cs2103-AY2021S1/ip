package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A command to display tasks in the task list. */
public class ListCommand extends Command {

    /** The full command given by the user. */
    private String fullCommand;

    /**
     * Constructs a ListCommand.
     *
     * @param fullCommand The input given by the user.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command by listing the tasks.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui       The UI of the bot.
     * @param storage  The storage system of the bot.
     * @throws DukeException If there is something wrong with the input format given by the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.processList(fullCommand);
    }
}
