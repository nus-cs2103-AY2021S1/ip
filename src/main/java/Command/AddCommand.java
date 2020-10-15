package Command;

import DukeComponent.Ui;
import TaskList.TaskList;
import Tasks.Task;

public class AddCommand extends Command {
    private static Integer lastIndex;

    private Task task;

    public AddCommand(Task task) {
        super(CommandType.ADD);
        this.task = task;
    }

    @Override
    public String act(TaskList list) {
        assert (task != null);
        list.addTask(task);
        lastIndex = list.size() - 1;
        return Ui.getAddTaskMessage(task, list.size());
    }

    @Override
    public String undo(TaskList list) {
        if (lastIndex != null) {
            Integer temp = lastIndex;
            lastIndex = null;
            return Ui.getUndoAddMessage(list.remove(temp), list.size());
        } else {
            return Ui.getCannotUndoMessage();
        }
    }
}
