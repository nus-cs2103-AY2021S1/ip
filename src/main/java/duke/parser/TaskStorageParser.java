package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeParseException;
import duke.storage.Storable;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Datetime;

/** Represents a class that parses lines in a saved storage text file into actual <code>Task</code>. */
public class TaskStorageParser implements StorageParser<Task> {
    private static final String IS_COMPLETED = "1";
    private static final String NOT_COMPLETED = "0";

    private static final int TODO_LENGTH = 3;
    private static final int DEADLINE_LENGTH = 4;
    private static final int EVENT_LENGTH = 4;

    private static final int COMPLETED_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DATETIME_INDEX = 3;

    private boolean getCompletionStatus(
            String[] storageTask, String storageString) throws DukeParseException {

        assert storageTask.length >= COMPLETED_INDEX : "Storage string does not have completion status";

        String completedSymbol = storageTask[COMPLETED_INDEX];
        switch (completedSymbol) {
        case IS_COMPLETED:
            return true;
        case NOT_COMPLETED:
            return false;
        default:
            String msg = String.format(
                    "It appears the completion status of this task: '%s' is corrupted", storageString);
            throw new DukeParseException(msg);
        }
    }

    private Todo createTodo(String[] storageTask, String storageString) throws DukeParseException {
        if (storageTask.length < TODO_LENGTH) {
            String msg = String.format("It appears this todo: '%s' is corrupted.", storageString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = getCompletionStatus(storageTask, storageString);
        return new Todo(storageTask[DESCRIPTION_INDEX], isCompleted);
    }

    private Deadline createDeadline(
            String[] storageTask, String storageString) throws DukeParseException {
        if (storageTask.length < DEADLINE_LENGTH) {
            String msg = String.format("It appears this deadline: '%s' is corrupted.", storageString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = getCompletionStatus(storageTask, storageString);

        try {
            LocalDateTime dateTime = Datetime.parseDateTimeString(
                    storageTask[DATETIME_INDEX], Deadline.DATE_FORMAT_OUTPUT);
            return new Deadline(storageTask[DESCRIPTION_INDEX], isCompleted, dateTime);

        } catch (DukeParseException exception) {
            String msg = String.format("It appears the datetime of this deadline: '%s' is corrupted. ",
                    storageString);
            throw new DukeParseException(msg + exception.getMessage());
        }
    }

    private Event createEvent(String[] storageTask, String storageString) throws DukeParseException {
        if (storageTask.length < EVENT_LENGTH) {
            String msg = String.format("It appears this event: '%s' is corrupted.", storageString);
            throw new DukeParseException(msg);
        }
        boolean isCompleted = getCompletionStatus(storageTask, storageString);

        try {
            LocalDateTime time = Datetime.parseTimeString(
                    storageTask[DATETIME_INDEX], Event.TIME_FORMAT_OUTPUT);
            return new Event(storageTask[DESCRIPTION_INDEX], isCompleted, time);

        } catch (DukeParseException exception) {
            String msg = String.format("It appears the time of this event: '%s' is corrupted. ",
                    storageString);
            throw new DukeParseException(msg + exception.getMessage());
        }
    }

    /**
     * Converts a <code>String</code> from the storage text file into its associated <code>Task</code>.
     *
     * @param storageString the <code>String</code> that is to be converted.
     * @return the associated <code>Task</code> from the given <code>String</code>.
     * @throws DukeParseException if this does not recognise the
     * format of the <code>String</code> being parsed.
     */
    @Override
    public Task parseStorageString(String storageString) throws DukeParseException {
        String[] storageTask = storageString.split(Storable.DELIMITER);
        assert storageTask.length > 0 : "There is an error in the splitting of the storageTaskString";
        switch(storageTask[0]) {
        case Todo.TODO_SYMBOL:
            return createTodo(storageTask, storageString);
        case Deadline.DEADLINE_SYMBOL:
            return createDeadline(storageTask, storageString);
        case Event.EVENT_SYMBOL:
            return createEvent(storageTask, storageString);
        default:
            String err = String.format("It appears this line: '%s' is corrupted.", storageString);
            throw new DukeParseException(err);
        }
    }
}
