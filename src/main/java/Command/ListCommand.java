package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class ListCommand extends Command{
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void act(TaskList list) {
        Ui.list(list);
    }
}
