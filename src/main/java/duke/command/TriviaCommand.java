package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class TriviaCommand extends Command {

    /** Stores Trivia associated with this command. */
    private Task task;

    /**
     * Initialzes Trivia Command Object.
     *
     * @param trivia trivia associated with Command.
     */
    public TriviaCommand(Task trivia) {
        task = trivia;
    }

    /**
     * Contains a series of logic for the execution of Command specified by the user.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        boolean hasDuplicates = taskItems.findIfDuplicateExists(task);
        if (hasDuplicates) {
            throw new DukeException("Duplicate Trivia Added");
        }
        taskItems.addTask(task);
        storage.saveTaskToMemory(taskItems.getAll());
        return ui.addTriviaReply(task, taskItems);
    }

    /**
     * Returns instruction to Duke class whether to terminate program.
     *
     * @return bool value to terminate or not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
