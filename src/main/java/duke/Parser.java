package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.StatisticsCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Parser object deals with loading tasks from the file and saving tasks in the file.
 */
public class Parser {
    private static String[] inputArr;

    /**
     * Translates user input into meaningful commands and details.
     *
     * @param userInput User input.
     * @return Command to be done.
     * @throws DukeException If command is wrong. Or if details of the task is wrong.
     */
    public static Command parse(String userInput) throws DukeException {
        inputArr = userInput.split("\\s+");
        String command = inputArr[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "done": {
            try {
                String lastChar = inputArr[inputArr.length - 1];
                return new DoneCommand(Integer.parseInt(lastChar) - 1);
            } catch (Exception e) {
                throw new DukeException("Please add the index of the task!!");
            }
        }
        case "delete": {
            try {
                String lastChar = inputArr[inputArr.length - 1];
                return new DeleteCommand(Integer.parseInt(lastChar) - 1);
            } catch (Exception e) {
                throw new DukeException("Please add the index of the task!!");
            }
        }
        case "bye":
            return new ExitCommand();
        case "find":
            String[] keyword = getKeyword();
            return new FindCommand(keyword);
        case "statistics":
            return new StatisticsCommand();
        case "deadline":
            String desc;
            String dateTime;
            Task task;
            try {
                desc = getTaskDescription();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the description of the deadline!!");
            }
            try {
                dateTime = getTaskTimeDate();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the date and time of the deadline!!");
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter the dates as YYYY-MM-DD");
            }
            task = new Deadline(desc, dateTime);
            return new AddCommand(task);
        case "event":
            try {
                desc = getTaskDescription();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the description of the event!!");
            }
            try {
                dateTime = getTaskTimeDate();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the date and time of the event!!");
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter the dates as YYYY-MM-DD");
            }
            task = new Event(desc, dateTime);
            return new AddCommand(task);
        case "todo":
            try {
                desc = getTaskDescription();
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the description of the todo!!");
            }
            task = new Todo(desc);
            return new AddCommand(task);
        default:
            throw new DukeException("Sorry I dont understand!! Please give a proper command.");
        }
    }

    private static String getTaskDescription() {
        StringBuilder desc = new StringBuilder();
        int i = 1;
        while ((i < inputArr.length) && (!inputArr[i].contains("/by")) && (!inputArr[i].contains("/at"))) {
            // Get description of the task, which is after the command in inputArr
            desc.append(inputArr[i]).append(" ");
            i++;
        }
        return desc.substring(0, desc.length() - 1);
    }

    private static String getTaskTimeDate() {
        String dateTime = "";
        int i = 0;
        while (!inputArr[i].contains("/by") && (!inputArr[i].contains("/at"))) {
            // Get description of the task, which is after the command in inputArr
            i++;
        }
        i++;
        while (i < inputArr.length) {
            dateTime = dateTime + inputArr[i] + " ";
            i++;
        }
        String cleanedDateTime = dateTime.substring(0, dateTime.length() - 1);
        isValidDate(cleanedDateTime);
        return cleanedDateTime;
    }

    private static String[] getKeyword() {
        return Arrays.copyOfRange(inputArr, 1, inputArr.length);
    }

    private static void isValidDate(String dateString) {
        LocalDate d = LocalDate.parse(dateString);
        d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
