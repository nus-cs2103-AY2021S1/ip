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
            default:
                return Ui.ignoreMessage();
            }
        } else if (e instanceof DateTimeParseException) {
            return Ui.dateFormatReminder();
        } else {
            return Ui.ignoreMessage();
        }
    }
}
