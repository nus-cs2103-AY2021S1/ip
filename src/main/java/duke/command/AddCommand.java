package duke.command;

import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     * @param extra String description of the task to be added.
     */
    public AddCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui)
            throws DukeInvalidDateException,
            DukeInvalidArgumentException {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        Task taskToBeAdded = null;
        if (command.equals("todo")) {
            taskToBeAdded = new Todo(extra);
        }
        try {
            if (command.equals("deadline")) {
                taskToBeAdded = new Deadline(
                        extra.substring(0, extra.indexOf("/") - 1),
                        extra.substring(extra.indexOf("/") + 4));
            }
            if (command.equals("event")) {
                taskToBeAdded = new Event(
                        extra.substring(0, extra.indexOf("/") - 1),
                        extra.substring(extra.indexOf("/") + 4));
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException(command);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException(command);
        }
        tasks.addTask(taskToBeAdded);
        return ui.printAdded(taskToBeAdded, tasks.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AddCommand;
    }
}
