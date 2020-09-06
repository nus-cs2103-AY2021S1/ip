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
            String responseMessage = "";
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
                responseMessage = sb.delete(sb.length() - 3, sb.length() - 1).toString();
            } else {
                responseMessage = String.format(
                        "You do not have any deadlines or events happening on %s! :)",
                        date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            }
            boolean shouldExit = getIsExit();
            assert !shouldExit : "shouldExit should be false";
            return new CommandResponse(responseMessage, shouldExit);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }

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
