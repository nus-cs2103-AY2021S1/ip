package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Datetime;

/**
 * Represents a class that parses lines in a saved storage text file into actual <code>Task</code>.
 * This class also parses <code>Task</code> into <code>Strings</code>
 * that will be saved into the storage text file.
 */
public class StorageParser {
    private static final String IS_COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";
    private static final String DELIMITER = ";";
    private static final int TODO_COMMAND_LENGTH = 3;
    private static final int DEADLINE_COMMAND_LENGTH = 4;
    private static final int EVENT_COMMAND_LENGTH = 4;

    /**
     * Converts a <code>Task</code> to a <code>String</code> that will be saved onto the storage text file.
     * @param task the <code>Task</code> that is to be converted.
     * @return the <code>String</code> representing the <code>Task</code>.
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

    private Todo createTodo(String[] storageTask, String storageTaskString) throws DukeParseException {
        if (storageTask.length < TODO_COMMAND_LENGTH) {
            String msg = String.format("It appears this todo: '%s' is corrupted.", storageTaskString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        return new Todo(storageTask[2], isCompleted);
    }

    private Deadline createDeadline(
            String[] storageTask, String storageTaskString) throws DukeParseException {
        if (storageTask.length < DEADLINE_COMMAND_LENGTH) {
            String msg = String.format("It appears this deadline: '%s' is corrupted.", storageTaskString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        try {
            LocalDateTime dateTime = Datetime.parseDateTimeString(
                    storageTask[3], Deadline.DATE_FORMAT_OUTPUT);
            return new Deadline(storageTask[2], isCompleted, dateTime);
        } catch (DukeParseException exception) {
            String msg = String.format("It appears the datetime of this deadline: '%s' is corrupted.",
                    storageTaskString);
            throw new DukeParseException(msg);
        }
    }

    private Event createEvent(String[] storageTask, String storageTaskString) throws DukeParseException {
        if (storageTask.length < EVENT_COMMAND_LENGTH) {
            String msg = String.format("It appears this event: '%s' is corrupted.", storageTaskString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = storageTask[1].equals(IS_COMPLETED);
        try {
            LocalDateTime time = Datetime.parseTimeString(storageTask[3], Event.TIME_FORMAT_OUTPUT);
            return new Event(storageTask[2], isCompleted, time);
        } catch (DukeParseException exception) {
            String msg = String.format("It appears the time of this event: '%s' is corrupted.",
                    storageTaskString);
            throw new DukeParseException(msg);
        }
    }

    /**
     * Converts a <code>String</code> from the storage text file into its associated <code>Task</code>.
     * @param storageTaskString the <code>String</code> that is to be converted.
     * @return the associated <code>Task</code> from the given <code>String</code>.
     * @throws DukeParseException if this does not recognise the
     * format of the <code>String</code> being parsed.
     */
    public Task convertStorageToTask(String storageTaskString) throws DukeParseException {
        String[] storageTask = storageTaskString.split(DELIMITER);
        switch(storageTask[0]) {
        case Todo.TODO_SYMBOL:
            return createTodo(storageTask, storageTaskString);
        case Deadline.DEADLINE_SYMBOL:
            return createDeadline(storageTask, storageTaskString);
        case Event.EVENT_SYMBOL:
            return createEvent(storageTask, storageTaskString);
        default:
            String err = String.format("It appears this line: '%s' is corrupted.", storageTaskString);
            throw new DukeParseException(err);
        }
    }
}
