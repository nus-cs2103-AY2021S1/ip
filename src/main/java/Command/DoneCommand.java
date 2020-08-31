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
    public void act(TaskList list) {
        list.setCompleted(whichTask);
        Ui.doneMessage(list.get(whichTask));
    }
}
