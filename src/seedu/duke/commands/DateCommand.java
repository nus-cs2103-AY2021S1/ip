package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;
import seedu.duke.DukeException;
import java.time.LocalDate;

public class DateCommand extends Command {
    private LocalDate time;

    public DateCommand(LocalDate time) {
        super("done");
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listTasksOn(time);
        // ui.showDateMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
