package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Datetime;

/**
 * Represents an object that parses lines in a saved storage text file into actual Task objects.
 * This object also parses Task objects into Strings that will be saved into the storage text file.
 */
public class StorageParser {
    private static final String IS_COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";
    private static final String DELIMITER = ";";

    /**
     * Converts a Task to a String that will be saved onto the Storage text file.
     * @param task the task that is to be converted.
     * @return the String representing the Task.
     */
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

    /**
     * Converts a String from the storage text file into its associated Task.
     * @param storageTaskString the String that is to be converted.
     * @return the associated Task from the given String.
     * @throws DukeException if this does not recognise the format of the String being parsed.
     */
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
