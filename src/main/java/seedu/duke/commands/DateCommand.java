package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

import java.time.LocalDate;

/**
 * Represents the command to list out tasks on a specific date.
 */
public class DateCommand extends Command {
    private LocalDate time;

    /**
     * Constructor for DateCommand.
     * @param time
     */
    public DateCommand(LocalDate time) {
        super("done");
        this.time = time;
    }

    /**
     * Executes the command to list out tasks on a specific date.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     * @throws DukeException If there is no date provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listTasksOn(time);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
