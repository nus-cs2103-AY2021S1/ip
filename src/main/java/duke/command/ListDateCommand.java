package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class ListDateCommand implements Command {
    private final LocalDate date;

    /**
     * Class constructor with specified Date.
     * @param date The date specified.
     */
    public ListDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Performs the printing of all the Task on the specified date.
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWindow(tasks.getTasksOnDate(date));
        return true;
    }
}
