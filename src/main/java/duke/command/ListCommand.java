package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) {
        return ui.getPrintListResponseAsString(tasks);
    }

    @Override
    public void undo(TaskList tasks) {
        return;
    }
}
