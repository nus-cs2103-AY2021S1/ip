package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import duke.ui.Ui;

import java.util.Arrays;

import static duke.task.Deadline.DELIMITER_BY;
import static duke.task.Event.DELIMITER_AT;

import static duke.ui.Message.concatLines;
import static duke.ui.Message.MESSAGE_ADDED;
import static duke.ui.Ui.LINE_SEPARATOR;
import static duke.ui.Message.getTotalTaskMessage;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private final String keyword;
    private final String stringWithoutKeyword;

    /**
     * Constructs an <code>AddCommand</code> Object given description.
     *
     * @param taskDescription The task description from user input, including the command word.
     */
    public AddCommand(String taskDescription) {
        assert taskDescription != null;
      
        String[] splitWords = taskDescription.split("\\s+");
        this.keyword = splitWords[0]; // todo, deadline, event
        this.stringWithoutKeyword = getStringWithoutKeyword(splitWords);
    }

    private static String getStringWithoutKeyword(String[] strArr) {
        return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
    }

    /**
     * Retrieves both the date and time of an event or a deadline.
     *
     * @param str A string containing the description, date and time of an event or
     *            deadline.
     * @param delimiter The delimiter /at or /by.
     * @return The date and time of an event or a deadline.
     * @throws DukeException If the date and time of an event or a deadline is empty.
     */
    private static String getDateAndTime(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");

        boolean isDelimiterByMatched = delimiter.equals(DELIMITER_BY);
        boolean isDelimiterAtMatched = delimiter.equals(DELIMITER_AT);

        boolean isDeadlineTimeEmpty = splitString[splitString.length - 1].equals("/by");
        boolean isEventTimeEmpty = splitString[splitString.length - 1].equals("/at");

        if (isDelimiterByMatched && isDeadlineTimeEmpty) {
            throw new DukeException("the due date and time of a deadline cannot by empty.");
        } else if (isDelimiterAtMatched && isEventTimeEmpty) {
            throw new DukeException("the date and time of an event cannot by empty.");
        }

        if (str.split(delimiter).length == 1) {
            // (deadline/event by/at ...)
            return str.split(delimiter)[0];
        }
        // (deadline/event ... by/at ...)
        return str.split(delimiter)[1];
    }

    /**
     * Retrieves the description of an event or a deadline.
     *
     * @param str A string containing the description, date and time of an event or
     *            deadline.
     * @param delimiter The delimiter /at or /by.
     * @return The description of an event or a deadline.
     * @throws DukeException If the description of an event or a deadline is empty.
     */
    private static String getDescription(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");

        boolean isDelimiterByMatched = delimiter.equals(DELIMITER_BY);
        boolean isDelimiterAtMatched = delimiter.equals(DELIMITER_AT);

        boolean isDeadlineDescrEmpty = splitString[0].equals("/by");
        boolean isEventDescrEmpty = splitString[0].equals("/at");

        if (isDelimiterByMatched && isDeadlineDescrEmpty) {
            throw new DukeException("the description of a deadline cannot by empty.");
        } else if (isDelimiterAtMatched && isEventDescrEmpty) {
            throw new DukeException("the description of an event cannot by empty.");
        }
        return str.split(delimiter)[0];
    }

    /**
     * Returns true if the description, date and time of an event or deadline is empty.
     *
     * @param delimiter The delimiter /at or /by.
     * @return Returns true if the description, date and time of an event or deadline is empty.
     */
    private boolean isDescrDateAndTimeEmpty(String delimiter) {
        return this.stringWithoutKeyword.startsWith(delimiter)
                && this.stringWithoutKeyword.endsWith(delimiter);
    }

    /**
     * Creates a new <code>Event</code> Object.
     */
    private Event createEventTask(String string) throws DukeException {
        String description = getDescription(string, DELIMITER_AT);
        String dateAndTime = getDateAndTime(string, DELIMITER_AT);
        return new Event(description, dateAndTime);
    }

    /**
     * Creates a new <code>Deadline</code> Object.
     */
    private Deadline createDeadlineTask(String string) throws DukeException {
        String dateAndTime = getDateAndTime(string, DELIMITER_BY);
        String description = getDescription(string, DELIMITER_BY);
        return new Deadline(description, dateAndTime);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch (this.keyword) {
        case "todo":
            if (this.stringWithoutKeyword.isEmpty()) {
                throw new DukeException("the description of a todo cannot be empty.");
            }
            newTask = new ToDo(this.stringWithoutKeyword);
            break;
        case "deadline":
            if (this.stringWithoutKeyword.isEmpty() || isDescrDateAndTimeEmpty("/by")) {
                throw new DukeException("the description and the due date and time of a deadline cannot be empty.");
            }
            newTask = createDeadlineTask(this.stringWithoutKeyword);
            break;
        case "event":
            if (this.stringWithoutKeyword.isEmpty() || isDescrDateAndTimeEmpty("/at")) {
                throw new DukeException("the description and the date and time of an event cannot be empty.");
            }
            newTask = createEventTask(this.stringWithoutKeyword);
            break;
        default:
            throw new DukeException("I don't understand what you are saying. :(");
        }
        taskList.add(newTask);
        storage.saveTasks(taskList);

        assert taskList.get(taskList.size()).equals(newTask) : "Task is not added into the taskList!";

        return concatLines(MESSAGE_ADDED, newTask.toString(), LINE_SEPARATOR, getTotalTaskMessage(taskList));
    }
}
