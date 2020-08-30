package command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * View all tasks in the current task list based on a certain date
 */
public class ViewallCommand extends Command {
    private LocalDate date;

    public ViewallCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * View all tasks in the current task list based on a certain date
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filtered = tasks.viewAll(date);
        ui.print("Here are the tasks on given date:");
        String response = "Here are the tasks on given date:\n";
        for (int i = 0; i < filtered.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, filtered.show(i)));
            response += String.format("%d. %s%n", i + 1, filtered.show(i));
        }
        return new CommandResult(response);
    }
}
