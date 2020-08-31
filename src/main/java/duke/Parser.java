package duke;

import duke.command.AddCommand;
import duke.command.GetCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.Command;
import duke.exception.DateException;
import duke.exception.DukeException;
import duke.exception.MissingInformationException;
import duke.format.DateFormat;
import duke.task.TaskType;

import java.util.Date;

/**
 * Represents a decoder to make sense of the user input.
 */
public class Parser {

    /**
     * Transforms a given user command from string type to Command type.
     * @param fullCommand the command user inputs
     * @return a Command that can be executed
     * @throws MissingInformationException If there are missing information in user input.
     * @throws DateException If the date provided is of the wrong format.
     * @throws DukeException If the user command is invalid.
     */
    public static Command parse(String fullCommand) throws MissingInformationException, DateException, DukeException {
        String[] commandArr = fullCommand.split(" ", 2);
        if (commandArr[0].equals("todo")) {
            return parseTodo(commandArr);
        } else if (commandArr[0].equals("deadline")) {
           return parseDeadline(commandArr);
        } else if (commandArr[0].equals("event")) {
            return parseEvent(commandArr);
        } else if (commandArr[0].equals("list")) {
            return new ListCommand();
        } else if (commandArr[0].equals("done")) {
            return parseDone(commandArr);
        } else if (commandArr[0].equals("delete")) {
            return parseDelete(commandArr);
        } else if (commandArr[0].equals("get")) {
            return parseGet(commandArr);
        } else if (commandArr[0].equals("find")) {
            return parseFind(commandArr);
        } else if (commandArr[0].equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException("I am sorry, I don't know what that means :(");
        }

    }

    private static void checkDescription(String[] commandArr, String message) throws MissingInformationException {
        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException(message);
        }
    }

    private static Command parseDelete(String[] commandArr) throws MissingInformationException {
        int index = getIndex(commandArr);
        return new DeleteCommand(index);
    }

    private static Command parseDone(String[] commandArr) throws MissingInformationException {
        int index = getIndex(commandArr);
        return new DoneCommand(index);
    }

    private static Command parseGet(String[] commandArr) throws MissingInformationException, DateException {
        checkDescription(commandArr, "Date is missing!");
        Date date = DateFormat.parseDate(commandArr[1]);
        return new GetCommand(date);

    }

    private static Command parseFind(String[] commandArr) throws MissingInformationException {
        checkDescription(commandArr, "Key word is missing!");
        return new FindCommand(commandArr[1]);
    }

    private static Command parseTodo(String[] commandArr) throws MissingInformationException {
        checkDescription(commandArr, "The description of a todo cannot be empty.");
        return new AddCommand(TaskType.TODO, commandArr[1]);
    }

    private static Command parseDeadline(String[] commandArr) throws MissingInformationException, DateException {
        checkDescription(commandArr, "The description of a deadline cannot be empty.");

        String[] descriptionArr = commandArr[1].split(" /by ", 2);
        if (descriptionArr.length < 2 || descriptionArr[1].isBlank()) {
            throw new MissingInformationException("Deadline is missing a date.");
        }
        Date date = DateFormat.parseDate(descriptionArr[1]);
        return new AddCommand(TaskType.DEADLINE, descriptionArr[0], date);

    }

    private static Command parseEvent(String[] commandArr) throws MissingInformationException, DateException {
        checkDescription(commandArr, "The description of an event cannot be empty.");

        String[] descriptionArr = commandArr[1].split(" /at ", 2);
        if (descriptionArr.length < 2 || descriptionArr[1].isBlank()) {
            throw new MissingInformationException("Event is missing a date.");
        }
        Date date = DateFormat.parseDate(descriptionArr[1]);
        return new AddCommand(TaskType.EVENT, descriptionArr[0], date);

    }

    /**
     * Extracts the task number referred to in the user command.
     * @param  commandArr the user input represented in the form of an array
     * @return the task number
     * @throws MissingInformationException If task number is not provided.
     */
    private static int getIndex(String[] commandArr) throws MissingInformationException {

        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException("Task number is missing!");
        }
        return Integer.parseInt(commandArr[1]);
    }

}
