package main.java.seedu.duke.commands;

import main.java.seedu.duke.DukeException;
import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

import java.time.LocalDate;

/**
 * Represents the command to list out tasks on a specific date.
 */
public class DateCommand extends Command {
    private LocalDate time;

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
