package duke.Command;

import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.Task;
import duke.Task.TaskList;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.ToDo;

import duke.Ui.Message;
import duke.Ui.Ui;

import java.util.Arrays;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private final String desc;

    /**
     * Constructs an <code>AddCommand</code> Object given description.
     *
     * @param taskDescription The task description from user input, excluding the command word
     */
    public AddCommand(String taskDescription) {
        this.desc = taskDescription;
    }

    public static String getStringWithoutKeyword(String[] strArr) {
        return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
    }

    private static String getDate(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");
        if (delimiter.equals(Deadline.delimiterBy) && splitString[splitString.length - 1].equals("/by")) {
            throw new DukeException("The due time of a deadline cannot by empty");
        } else if (delimiter.equals(Event.delimiterAt) && splitString[splitString.length - 1].equals("/at")) {
            throw new DukeException("The time of an event cannot by empty");
        }

        if (str.split(delimiter).length == 1) {
            // (deadline/event by/at ...)
            return str.split(delimiter)[0];
        }
        // (deadline/event ... by/at ...)
        return str.split(delimiter)[1];
    }


    private static String getWithoutDelimiter(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");
        if (delimiter.equals(Deadline.delimiterBy) && splitString[0].equals("/by")) {
            throw new DukeException("The description of a deadline cannot by empty");
        } else if (delimiter.equals(Event.delimiterAt) && splitString[0].equals("/at")) {
            throw new DukeException("The description of an event cannot by empty");
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

        String date;
        String stringWithoutDelimiter;

        switch (keyword) {
        case "todo":
            if (words.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            newTask = new ToDo(stringWithoutKeyword);
            break;
        case "deadline":
            if (words.length == 1 || (words[1].equals("/by") && words.length == 2)) {
                throw new DukeException("The description and the due time of a deadline cannot be empty.");
            }

            date = getDate(stringWithoutKeyword, Deadline.delimiterBy);
            stringWithoutDelimiter = getWithoutDelimiter(stringWithoutKeyword, Deadline.delimiterBy);
            newTask = new Deadline(stringWithoutDelimiter, date);
            break;
        case "event":
            if (words.length == 1 || (words[1].equals("/at") && words.length == 2)) {
                throw new DukeException("The description and the time of an event cannot be empty.");
            }

            date = getDate(stringWithoutKeyword, Event.delimiterAt);
            stringWithoutDelimiter = getWithoutDelimiter(stringWithoutKeyword, Event.delimiterAt);
            newTask = new Event(stringWithoutDelimiter, date);

            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        taskList.add(newTask);
        storage.saveTasks(taskList);

        return Message.concatLines(Message.MESSAGE_ADDED, newTask.toString(),
                Ui.LINE_SEPARATOR, Message.getTotalTaskMessage(taskList));
    }
}