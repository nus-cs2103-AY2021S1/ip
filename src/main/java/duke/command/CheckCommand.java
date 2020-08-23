package duke.command;

import duke.storage.*;
import duke.task.*;
import duke.ui.Ui;

import java.time.LocalDate;

import java.util.ArrayList;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        ui.printCheckStatement(date);
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getDate() == null) {
                continue;
            }

            if (lib.get(i).getDate().equals(date)) {
                ui.showTask(lib.get(i).toString());
            }
        }
        ui.printEndLine();
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
