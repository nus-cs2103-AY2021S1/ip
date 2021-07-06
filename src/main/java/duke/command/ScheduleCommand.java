package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class ScheduleCommand implements Command {
    LocalDate date;

    public ScheduleCommand(LocalDate date) {
        assert date != null : "Schedule date  should not be empty";
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.isEmpty()) {
            return ui.format("There are no items in the list right now.");
        } else {
            return ui.format(tasks.filterByDate(date).itemize("Here are the tasks on this day in your list:"));
        }
    }
}
