package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class ListDateCommand implements Command {
    private final LocalDate date;

    public ListDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWindow(tasks.getTasksOnDate(date));
        return true;
    }
}
