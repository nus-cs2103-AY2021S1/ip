package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DateFilterCommand extends Command {
    private LocalDate date;

    public DateFilterCommand(LocalDate date) {
        this.commandName = "DateFilter";
        this.date = date;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showDateFilterList();
        list.printList(date);
    }
}
