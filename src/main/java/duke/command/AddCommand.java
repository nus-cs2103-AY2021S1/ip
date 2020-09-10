package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.dukeexception.DukeException;
import duke.dukeexception.WrongDeadlineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;



/**
 * Command that adds a task to the user's list when executed.
 */
public class AddCommand extends Command {
    /** Represents the type of the command used by the user */
    private CommandType commandType;
    /** Additional information needed for executing the command */
    private final String description;

    /**
     * Public constructor.
     *
     * @param commandType Either <code>TODO</code>, <code>Deadline</code>,
     *                    or <code>Event</code>.
     * @param description Name of the task (and date and time if
     *                    <code>Deadline</code> or <code>Event</code>.
     */
    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        String[] descElements = this.description.split(" /by ");
        String taskName = descElements[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Task task = null;
        if (this.commandType == CommandType.TODO) {
            task = new Todo(taskName);
        } else if (this.commandType == CommandType.DEADLINE) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(descElements[1], formatter);
                task = new Deadline(taskName, dateTime);
            } catch (DateTimeParseException e) {
                throw new WrongDeadlineException("deadline", "/by");
            }
        } else if (this.commandType == CommandType.DEADLINE) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(descElements[1], formatter);
                task = new Event(taskName, dateTime);
            } catch (DateTimeParseException e) {
                throw new WrongDeadlineException("event", "/at");
            }
        }

        if (task != null) {
            tasks.addTask(task, storage);

            return ui.returnReply("Orh. I added:" + "\n  " + task.toString()
                    + "\nNow you got " + tasks.getListLength() + " things in the list.");
        } else {
            throw new DukeException("Something went wrong! Try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
