package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printList(taskList);
    }
}