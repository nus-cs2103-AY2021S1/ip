package duke.command;

import duke.Storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import duke.ui.Message;
import duke.ui.Ui;

import java.util.Arrays;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private final String desc;

    /**
     * Constructs an <code>AddCommand</code> Object given description.
     *
     * @param taskDescription The task description from user input, excluding the command word.
     */
    public AddCommand(String taskDescription) {
        assert taskDescription != null;
        this.desc = taskDescription;
    }

    public static String getStringWithoutKeyword(String[] strArr) {
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

        boolean isDelimiterByMatched = delimiter.equals(Deadline.DELIMITER_BY);
        boolean isDelimiterAtMatched = delimiter.equals(Event.DELIMITER_AT);

        boolean isDeadlineTimeEmpty = splitString[splitString.length - 1].equals("/by");
        boolean isEventTimeEmpty = splitString[splitString.length - 1].equals("/at");

        if (isDelimiterByMatched && isDeadlineTimeEmpty) {
            throw new DukeException("The due date and time of a deadline cannot by empty.");
        } else if (isDelimiterAtMatched && isEventTimeEmpty) {
            throw new DukeException("The date and time of an event cannot by empty.");
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

        boolean isDelimiterByMatched = delimiter.equals(Deadline.DELIMITER_BY);
        boolean isDelimiterAtMatched = delimiter.equals(Event.DELIMITER_AT);

        boolean isDeadlineDescrEmpty = splitString[0].equals("/by");
        boolean isEventDescrEmpty = splitString[0].equals("/at");

        if (isDelimiterByMatched && isDeadlineDescrEmpty) {
            throw new DukeException("The description of a deadline cannot by empty.");
        } else if (isDelimiterAtMatched && isEventDescrEmpty) {
            throw new DukeException("The description of an event cannot by empty.");
        }
        return str.split(delimiter)[0];
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask;

        String[] words = this.desc.split("\\s+");
        String keyword = words[0]; // todo, deadline, event

        String stringWithoutKeyword;
        stringWithoutKeyword = getStringWithoutKeyword(words);

        String dateAndTime;
        String description;

        switch (keyword) {
        case "todo":
            if (words.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            newTask = new ToDo(stringWithoutKeyword);
            break;
        case "deadline":
            if (words.length == 1 || (words[1].equals("/by") && words.length == 2)) {
                throw new DukeException("The description and the due date and time of a deadline cannot be empty.");
            }

            dateAndTime = getDateAndTime(stringWithoutKeyword, Deadline.DELIMITER_BY);
            description = getDescription(stringWithoutKeyword, Deadline.DELIMITER_BY);
            newTask = new Deadline(description, dateAndTime);
            break;
        case "event":
            if (words.length == 1 || (words[1].equals("/at") && words.length == 2)) {
                throw new DukeException("The description and the date and time of an event cannot be empty.");
            }

            dateAndTime = getDateAndTime(stringWithoutKeyword, Event.DELIMITER_AT);
            description = getDescription(stringWithoutKeyword, Event.DELIMITER_AT);
            newTask = new Event(description, dateAndTime);
            break;
        default:
            throw new DukeException("Did you type the wrong command? Try again!");
        }

        taskList.add(newTask);
        storage.saveTasks(taskList);

        assert taskList.get(taskList.size()).equals(newTask) : "Task is not added into the taskList!";

        return Message.concatLines(Message.MESSAGE_ADDED, newTask.toString(),
                Ui.LINE_SEPARATOR, Message.getTotalTaskMessage(taskList));
    }
}
