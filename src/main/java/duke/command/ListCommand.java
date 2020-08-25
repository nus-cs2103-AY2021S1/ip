package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
