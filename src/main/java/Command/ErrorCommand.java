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
                return Ui.getEmptyTodoMessage();
            case DukeException.WRONG_DEADLINE:
                return Ui.getWrongDeadlineMessage();
            case DukeException.WRONG_EVENT:
                return Ui.getWrongEventMessage();
            case DukeException.EMPTY:
                return Ui.getEmptyInputMessage();
            case DukeException.WRONG_DONE_OR_DELETE:
                return Ui.getWrongDoneOrDeleteMessage(list.size());
            case DukeException.WRONG_FIND:
                return Ui.getWrongFindMessage();
            default:
                return Ui.getIgnoreMessage();
            }
        } else if (e instanceof DateTimeParseException) {
            return Ui.getDateFormatReminder();
        } else {
            return Ui.getIgnoreMessage();
        }
    }

    @Override
    public String undo(TaskList list) {
        return Ui.getCannotUndoMessage();
    }
}
