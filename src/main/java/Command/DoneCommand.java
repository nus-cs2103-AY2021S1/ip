package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class DoneCommand extends Command {
    private int whichTask;

    public DoneCommand(int whichTask) {
        super(CommandType.DONE);
        this.whichTask = whichTask;
    }

    @Override
    public String act(TaskList list) {
        list.setCompleted(whichTask);
        return Ui.doneMessage(list.get(whichTask));
    }
}
