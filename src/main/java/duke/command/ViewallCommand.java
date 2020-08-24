package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class ViewallCommand extends Command {
    LocalDate date;
    
    public ViewallCommand(LocalDate date) {
        this.date = date;
    }
    
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filtered = taskList.viewAll(date);
        ui.print("Here are the tasks on given date:");
        for (int i = 0; i < filtered.size(); i++) {
            ui.print(String.format("%d. %s%n", i + 1, filtered.show(i)));
        }
    }
}
