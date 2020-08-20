package duke.Command;

import duke.*;

import java.util.Arrays;

public class AddCommand extends Command {

    private final String desc;

    public AddCommand(String description) {
        this.desc = description;
    }

    public static String getStringWithoutKeyword(String[] strArr) {
        return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
    }

    public static String getDate(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");
        if (delimiter.equals(Deadline.delimiterBy) && splitString[splitString.length - 1].equals("/by")) {
            throw new DukeException("The due time of a deadline cannot by empty");
        } else if (delimiter.equals(Event.delimiterAt) && splitString[splitString.length - 1].equals("/at")) {
            throw new DukeException("The time of an event cannot by empty");
        }

        if (str.split(delimiter).length == 1) {
            return str.split(delimiter)[0]; // (deadline/event by/at ...)
        }
        return str.split(delimiter)[1]; // (deadline/event ... by/at ...)
    }


    public static String getWithoutDelimiter(String str, String delimiter) throws DukeException {
        String[] splitString = str.split("\\s+");
        if (delimiter.equals(Deadline.delimiterBy) && splitString[0].equals("/by")) {
            throw new DukeException("The description of a deadline cannot by empty");
        } else if (delimiter.equals(Event.delimiterAt) && splitString[0].equals("/at")) {
            throw new DukeException("The description of an event cannot by empty");
        }

        return str.split(delimiter)[0];
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask;

        String[] words = this.desc.split("\\s+");
        String keyword = words[0];

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
        ui.showTaskAdditionMessage(newTask, taskList);
    }
}
