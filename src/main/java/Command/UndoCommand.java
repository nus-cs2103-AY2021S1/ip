package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class UndoCommand extends Command {
    Command lastCommand;

    public UndoCommand(Command lastCommand) {
        super(Command.CommandType.UNDO);
        this.lastCommand = lastCommand;
    }

    @Override
    public String act(TaskList list) {
        if (lastCommand != null) {
            return lastCommand.undo(list);
        } else {
            return Ui.cannotUndoMessage();
        }
    }

    @Override
    public String undo(TaskList list) {
        return Ui.cannotUndoMessage();
    }
}
