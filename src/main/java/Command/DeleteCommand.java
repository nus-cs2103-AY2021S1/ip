package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class DeleteCommand extends Command {
    private int whichTask;

    public DeleteCommand(int whichTask) {
        super(CommandType.DELETE);
        this.whichTask = whichTask;
    }

    @Override
    public String act(TaskList list) {
        return Ui.deleteMessage(list.remove(whichTask), list.size());
    }
}
