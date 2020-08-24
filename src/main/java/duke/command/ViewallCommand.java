package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * View all tasks in the current task list based on a certain date
 */
public class ViewallCommand extends Command {
    LocalDate date;

    public ViewallCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * View all tasks in the current task list based on a certain date
     *
     * @param taskList current task list
     * @param ui       text ui interface
     * @param storage  storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filtered = taskList.viewAll(date);
        ui.print("Here are the tasks on given date:");
        for (int i = 0; i < filtered.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, filtered.show(i)));
        }
    }
}
