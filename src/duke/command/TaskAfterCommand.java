package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.time.LocalDate;

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
