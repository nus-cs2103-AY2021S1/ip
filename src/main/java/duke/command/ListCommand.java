package duke.command;

import duke.logic.Ui;
import duke.task.TaskList;

public class ListCommand implements Command {
    public ListCommand() {}
    @Override
    public String execute(TaskList tasks) {
        // Do UI stuff here
        return Ui.printList(tasks.printTaskList());
        // Do storage stuff here
    }
}
