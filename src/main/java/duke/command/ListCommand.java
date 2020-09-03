package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    public ListCommand() {}
    @Override
    public void execute(TaskList tasks) {
        // Do UI stuff here
        Ui.printList(tasks.printTodoList());
        // Do storage stuff here
    }
}
