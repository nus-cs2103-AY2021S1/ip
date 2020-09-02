package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a {@link Command} that displays tasks happening on a particular date.
 */
public class ShowCommand extends Command {
    /**
     * The date filter for the {@link Task}s.
     */
    private final LocalDate date;

    /**
     * Instantiates a new ShowCommand object.
     *
     * @param date The date to query for.
     */
    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Lists all the {@link Task}s that happen on or due at a certain date.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.showTasksOnDate(date);
    }
}
