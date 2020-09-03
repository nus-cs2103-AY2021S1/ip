package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    public ListCommand() {}
    @Override
    public String execute(TaskList tasks) {
        // Do UI stuff here
        return Ui.printList(tasks.printTodoList());
        // Do storage stuff here
    }
}
