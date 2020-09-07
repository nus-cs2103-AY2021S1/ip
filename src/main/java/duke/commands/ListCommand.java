package duke.commands;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    private final LocalDate date;

    public ListCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * List all tasks.
     * If a date is provided, list out tasks on that day.
     *
     * @param tasks tasklist object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return a string of all the (relevant) tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        if (date == null) {
            output = ui.showTask(tasks);
        } else {
            output = ui.showTask(tasks, date);
        }

        return output;
    }

}
