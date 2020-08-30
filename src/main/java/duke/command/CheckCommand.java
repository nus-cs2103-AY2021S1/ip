package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates the Command which checks and loads tasks which occur
 * on the date specified in the check command entered by the user.
 */
public class CheckCommand extends Command {

    private LocalDate date;

    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        String result = ui.printCheckStatement(date);
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getDate() == null) {
                continue;
            }

            if (lib.get(i).getDate().equals(date)) {
                result += ui.showTask(lib.get(i).toString());
            }
        }
        return result;
    }

}
