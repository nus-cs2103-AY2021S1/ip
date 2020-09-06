package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidTaskDateException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to retrieve Deadline and Event occurring on
 * a specific date from TaskList.
 */
public class RetrieveCommand extends Command {
    /** Date of Deadline and Event to be retrieved */
    private final LocalDate date;

    /**
     * Constructs a <code>RetrieveCommand</code> object.
     *
     * @param date Date of Deadline and Event to be retrieved.
     */
    public RetrieveCommand(LocalDate date) {
        super(false);
        this.date = date;
    }

    /**
     * Retrieves Deadline and Event that has the same LocalDate as the user input.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @throws InvalidTaskDateException If date and time format is invalid.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDateException {
        try {
            TaskList retrievedTasks = new TaskList();

            for (Task t : tasks.getList()) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    if (d.getDateTime().toLocalDate().isEqual(date)) {
                        retrievedTasks.addTask(t);
                    }
                }
                if (t instanceof Event) {
                    Event e = (Event) t;
                    if (e.getDateTime().toLocalDate().isEqual(date)) {
                        retrievedTasks.addTask(t);
                    }
                }
            }

            boolean shouldExit = getIsExit();
            assert !shouldExit : "shouldExit should be false";
            return new CommandResponse(createResponseMessage(retrievedTasks), shouldExit);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }
    }

    private String createResponseMessage(TaskList tasks) {
        if (tasks.getNumberOfTask() == 0) {
            return String.format(
                    "You do not have any deadlines or events happening on %s! :)",
                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        }
        return tasks.toString().replaceFirst(
                "tasks in your list:\n\t ",
                String.format("deadlines and events happening on %s:\n\t ",
                        date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof RetrieveCommand) {
            RetrieveCommand c = (RetrieveCommand) obj;
            return c.date.isEqual(this.date) && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}
