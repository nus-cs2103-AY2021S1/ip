package duke.command;

import duke.exceptions.DukeInvalidTaskDescriptionException;
import duke.exceptions.DukeInvalidTaskTimeException;
import duke.exceptions.DukeLoadingErrorException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
    public CommandResult execute(TaskList tasks, Output output, Storage storage)
            throws DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException,
            DukeLoadingErrorException {
        Task toAdd = tasks.addTask(tag, input);
        storage.save(tasks.getTasks());
        return new CommandResult(output.printAddTaskChatWindow(toAdd, tasks.getTasks().size()));

    }

}
