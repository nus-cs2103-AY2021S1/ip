package parser;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import utils.Utils;

import java.util.Date;

public class StorageParser {
    private static final String IS_COMPLETED = "Y";
    private static final String NOT_COMPLETED = "N";
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

    private Todo createTodo(String[] storageTask) {
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        return new Todo(storageTask[2], isCompleted);
    }

    private Deadline createDeadline(String[] storageTask) throws DukeException {
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        Date dateTime = Utils.parseDateTimeString(storageTask[3], Deadline.DATE_FORMAT_OUTPUT);
        return new Deadline(storageTask[2], isCompleted, dateTime);
    }

    private Event createEvent(String[] storageTask) throws DukeException {
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        Date time = Utils.parseDateTimeString(storageTask[3], Event.TIME_FORMAT_OUTPUT);
        return new Event(storageTask[2], isCompleted, time);
    }

    public Task convertStorageToTask(String storageTaskString) throws DukeException {
        String[] storageTask = storageTaskString.split(DELIMITER);
        switch(storageTask[0]) {
            case Todo.TODO_SYMBOL:
                return createTodo(storageTask);
            case Deadline.DEADLINE_SYMBOL:
                return createDeadline(storageTask);
            case Event.EVENT_SYMBOL:
                return createEvent(storageTask);
            default:
                String err = String.format("It appears this line '%s' is corrupted.", storageTaskString);
                throw new DukeException(err);
        }
    }
}
