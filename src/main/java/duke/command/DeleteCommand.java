package duke.command;

import duke.*;
import duke.exception.*;

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
