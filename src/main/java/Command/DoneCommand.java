package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class DoneCommand extends Command {
    private static Integer lastIndex;
    private Integer whichTask;

    public DoneCommand(int whichTask) {
        super(CommandType.DONE);
        this.whichTask = whichTask;
    }

    @Override
    public String act(TaskList list) {
        assert (whichTask != null);
        list.setCompleted(whichTask);
        lastIndex = whichTask;
        return Ui.getDoneMessage(list.get(whichTask));
    }

    @Override
    public String undo(TaskList list) {
        if (lastIndex != null) {
            list.setNotCompleted(lastIndex);
            int temp = lastIndex;
            lastIndex = null;
            return Ui.getUndoDoneMessage(list.get(temp));
        } else {
            return Ui.getCannotUndoMessage();
        }
    }
}
