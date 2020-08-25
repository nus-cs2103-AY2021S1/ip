package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.time.LocalDate;

public class ShowCommand extends Command {
    /**
     * The date filter for the {@link Task}s.
     */
    private final LocalDate date;

    /**
     * Instantiates a new ShowCommand object.
     * @param date The date to query for.
     */
    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Lists all the {@link Task}s that happen on or due at a certain date.
     * @param tasks The list of {@link Task}s.
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.showTasksOnDate(date));
    }
}
