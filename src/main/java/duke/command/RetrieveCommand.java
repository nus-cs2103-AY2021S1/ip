package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDateException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * @return Nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDateException {
        try {
            StringBuffer sb = new StringBuffer();
            int index = 1;
            sb.append(String.format("Here are the deadlines and events happening on %s:\n\t ",
                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
            boolean hasTasks = false;
            for (Task t : tasks.getList()) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    if (d.getDateTime().toLocalDate().isEqual(date)) {
                        hasTasks = true;
                        sb.append(index).append(".").append(t.toString()).append("\n\t ");
                        index++;
                    }
                }
                if (t instanceof Event) {
                    Event e = (Event) t;
                    if (e.getDateTime().toLocalDate().isEqual(date)) {
                        hasTasks = true;
                        sb.append(index).append(".").append(t.toString()).append("\n\t ");
                        index++;
                    }
                }
            }
            if (hasTasks) {
                ui.printMessage(sb.delete(sb.length() - 3, sb.length() - 1).toString());
            } else {
                    ui.printMessage(String.format(
                            "You do not have any deadlines or events happening on %s! :)",
                                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }

    }
}
