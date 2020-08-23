package duke.command;

import duke.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ViewCommand extends Command {
    private String date;

    public ViewCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.printViewTasks(tasks, LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date is not valid.");
        }
    }
}
