package main.java.seedu.duke.commands;

import main.java.seedu.duke.DukeException;
import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

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
