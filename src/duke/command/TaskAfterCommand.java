package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidDateFormatException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskAfterCommand extends Command {
    private LocalDate date;

    public TaskAfterCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.eachTaskAfter(date, tasks.getTasks());
        ui.sendMessage(message);
    }
}
