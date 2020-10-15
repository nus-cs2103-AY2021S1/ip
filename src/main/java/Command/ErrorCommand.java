package Command;

import DukeComponent.DukeException;
import DukeComponent.Ui;
import TaskList.TaskList;

import java.time.format.DateTimeParseException;

public class ErrorCommand extends Command {
    Exception e;
    public ErrorCommand(Exception e) {
        super(CommandType.ERROR);
        this.e = e;
    }

    @Override
    public String act(TaskList list) {
        if (e instanceof DukeException) {
            switch (e.getMessage()) {
            case DukeException.EMPTY_TODO:
                return Ui.emptyTodoMessage();
            case DukeException.WRONG_DEADLINE:
                return Ui.wrongDeadlineMessage();
            case DukeException.WRONG_EVENT:
                return Ui.wrongEventMessage();
            case DukeException.EMPTY:
                return Ui.emptyInputMessage();
            case DukeException.WRONG_DONE_OR_DELETE:
                return Ui.wrongDoneOrDeleteMessage(list.size());
            case DukeException.WRONG_FIND:
                return Ui.wrongFindMessage();
            default:
                return Ui.ignoreMessage();
            }
        } else if (e instanceof DateTimeParseException) {
            return Ui.dateFormatReminder();
        } else {
            return Ui.ignoreMessage();
        }
    }

    @Override
    public String undo(TaskList list) {
        return Ui.cannotUndoMessage();
    }
}
