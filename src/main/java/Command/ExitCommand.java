package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(){
        super(CommandType.EXIT);
    }

    @Override
    public String act(TaskList list) {
        return Ui.exitMessage();
    }

    @Override
    public String undo(TaskList list) {
        return Ui.cannotUndoMessage();
    }
}
