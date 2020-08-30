package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeInvalidTaskDescriptionException;
import duke.exception.DukeInvalidTaskTimeException;
import duke.exception.DukeLoadingErrorException;

import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddTaskCommand extends Command {

    /**
     * Class constructor.
     *
     * @param tag   A string representing the tag of the command.
     * @param input A string representing the user input.
     */
    public AddTaskCommand(String tag, String input) {
        super(tag, input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException,
            DukeLoadingErrorException {
        Task toAdd = tasks.addTask(tag, input);
        storage.save(tasks.getTasks());
        return ui.printAddTaskChatWindow(toAdd, tasks.getTasks().size());

    }

}
