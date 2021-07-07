package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public String act(TaskList list) {
        return Ui.list(list);
    }

    @Override
    public String undo(TaskList list) {
        return Ui.getCannotUndoMessage();
    }
}
