package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeInvalidListNumberInputException;
import duke.exception.DukeLoadingErrorException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input A string representing the user input.
     */
    public DeleteCommand(String input) {
        super("delete", input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException, DukeLoadingErrorException {
        ui.printDeleteTaskChatWindow(tasks.deleteTask(input), tasks.getTasksSize());
        storage.save(tasks.getTasks());
    }

}
