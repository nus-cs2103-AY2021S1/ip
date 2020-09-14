package duke.command;

import java.time.LocalDate;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Response;

public class ListDateCommand implements Command {
    private final LocalDate date;

    /**
     * Class constructor with specified Date.
     * @param date The date specified.
     */
    public ListDateCommand(LocalDate date) {
        assert(date != null);
        this.date = date;
    }

    /**
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
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
    public String execute(TaskList tasks, Response ui, Storage storage) {
        return (tasks.getTasksOnDate(date));
    }
}
