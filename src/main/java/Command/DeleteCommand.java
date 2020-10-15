package Command;

import DukeComponent.Ui;
import TaskList.TaskList;
import Tasks.Task;

public class DeleteCommand extends Command {
    private static Task lastTask;
    private static Integer lastTaskNumber;

    private Integer whichTask;

    public DeleteCommand(Integer whichTask) {
        super(CommandType.DELETE);
        this.whichTask = whichTask;
    }

    @Override
    public String act(TaskList list) {
        assert (whichTask != null);
        lastTaskNumber = whichTask;
        return Ui.getDeleteMessage(lastTask = list.remove(whichTask), list.size());
    }

    @Override
    public String undo(TaskList list) {
        if (lastTaskNumber != null && lastTask != null) {
            list.addTaskAt(lastTask, lastTaskNumber);
            Task temp = lastTask;
            lastTask = null;
            lastTaskNumber = null;
            return Ui.getUndoDeleteMessage(temp, list.size());
        } else {
            return Ui.getCannotUndoMessage();
        }
    }
}
