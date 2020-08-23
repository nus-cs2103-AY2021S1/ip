package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Datetime;

public class StorageParser {
    private static final String IS_COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";
    private static final String DELIMITER = ";";

    public String convertTaskToStorage(Task task) {
        String symbol = task.getTaskSymbol();
        String completed = task.isTaskCompleted()
                ? DELIMITER + IS_COMPLETED
                : DELIMITER + NOT_COMPLETED;
        String description = DELIMITER + task.getTaskDescription();
        String datetime = task.getTaskDatetime().map(d -> DELIMITER + d).orElse("");
        return symbol + completed + description + datetime + "\n";
    }

    private Todo createTodo(String[] storageTask, String storageTaskString) throws DukeException {
        if (storageTask.length < 3) {
            String msg = String.format("It appears this todo: '%s' is corrupted.", storageTaskString);
            throw new DukeException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        return new Todo(storageTask[2], isCompleted);
    }

    private Deadline createDeadline(String[] storageTask, String storageTaskString) throws DukeException {
        if (storageTask.length < 4) {
            String msg = String.format("It appears this deadline: '%s' is corrupted.", storageTaskString);
            throw new DukeException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        LocalDateTime dateTime = Datetime.parseDateTimeString(storageTask[3], Deadline.DATE_FORMAT_OUTPUT);
        return new Deadline(storageTask[2], isCompleted, dateTime);
    }

    private Event createEvent(String[] storageTask, String storageTaskString) throws DukeException {
        if (storageTask.length < 4) {
            String msg = String.format("It appears this event: '%s' is corrupted.", storageTaskString);
            throw new DukeException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        LocalDateTime time = Datetime.parseTimeString(storageTask[3], Event.TIME_FORMAT_OUTPUT);
        return new Event(storageTask[2], isCompleted, time);
    }

    public Task convertStorageToTask(String storageTaskString) throws DukeException {
        String[] storageTask = storageTaskString.split(DELIMITER);
        switch(storageTask[0]) {
        case Todo.TODO_SYMBOL:
            return createTodo(storageTask, storageTaskString);
        case Deadline.DEADLINE_SYMBOL:
            return createDeadline(storageTask, storageTaskString);
        case Event.EVENT_SYMBOL:
            return createEvent(storageTask, storageTaskString);
        default:
            String err = String.format("It appears this line '%s' is corrupted.", storageTaskString);
            throw new DukeException(err);
        }
    }
}
