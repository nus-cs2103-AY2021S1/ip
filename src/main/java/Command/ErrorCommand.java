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
    public void act(TaskList list) {
        if (e instanceof DukeException) {
            switch (e.getMessage()) {
            case DukeException.EMPTY_TODO:
                Ui.emptyTodoMessage();
            default:
                Ui.ignoreMessage();
            }
        } else if (e instanceof DateTimeParseException) {
            Ui.dateFormatReminder();
        } else {
            Ui.ignoreMessage();
        }
    }
}
