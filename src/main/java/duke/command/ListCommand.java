package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.printList(taskList);
    }
}