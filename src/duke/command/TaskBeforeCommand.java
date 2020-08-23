package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.time.LocalDate;

public class TaskBeforeCommand extends Command {
    private LocalDate date;

    public TaskBeforeCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.eachTaskBefore(date, tasks.getTasks());
        ui.sendMessage(message);
    }
}
