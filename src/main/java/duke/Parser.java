package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.command.AddCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;



/**
 * Provides a class to interprets user inputs and converts them into Commands
 * to be executed.
 */
public class Parser {

    /**
     * Represents date and time.
     */
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    /**
     * Represents date.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private static final String SPACE_SPLIT = " ";
    private static final String EVENT_SPLIT = " /at ";
    private static final String DEADLINE_SPLIT = " /by ";

    /**
     * Returns different Commands depending on the keywords that the user inputs.
     * These keywords include "bye", "list", "done", "delete", "todo",
     * "event", "deadline". Words that are not recognised will not be read
     * and the function will ask the user to input a valid keyword.
     *
     * @param userInput Input that the user typed in.
     * @return Commands depending on which keyword the user used.
     * @throws DukeException If user entered an invalid input.
     */
    public Command parse(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(SPACE_SPLIT);
        String userKeyword = userInputArr[0];
        try {
            switch (userKeyword) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "find":
                return parseFind(userInputArr, userInput);

            case "done":
            case "delete":
                return parseDoneOrDelete(userInputArr, userKeyword);

            case "todo":
                return parseTodo(userInputArr, userInput);

            case "deadline":
                return parseDeadline(userInputArr, userInput);

            case "event":
                return parseEvent(userInputArr, userInput);

            case "archive":
                return archiveEvent(userInputArr);

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (ParseException e) {
            throw new DukeException("Please enter a date and time in the format of \n"
                    + "dd/MM/yyyy HHmm (e.g. 02/12/2020 1530) "
                    + "or dd/MM/yyyy (e.g. 15/02/2020)\n");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a task number in numeric format\n");
        }
    }



    /**
     * Parses user input into the Find Command.
     * @param userInputArr User input as array.
     * @param userInput User input.
     * @return Find Command.
     * @throws DukeException if invalid input.
     */
    private Command parseFind(String[] userInputArr, String userInput) throws DukeException {
        if (userInputArr.length < 2) {
            throw new DukeException("Please enter a keyword to find\n");
        }
        String keyword = userInput.substring(5);
        return new FindCommand(keyword);
    }

    /**
     * Parses user input into Done or Delete Command.
     * @param userInputArr User input as array.
     * @param userKeyword Keyword as done or delete.
     * @return Done or Delete Command.
     * @throws DukeException if invalid input.
     */
    private Command parseDoneOrDelete(String[] userInputArr, String userKeyword)
            throws DukeException {
        if (userInputArr.length < 2) {
            throw new DukeException("Please enter a task number to be selected\n");
        }
        String taskNumber = userInputArr[1];

        int taskNumberInt = Integer.parseInt(taskNumber) - 1;
        if (userKeyword.equals("done")) {
            return new DoneCommand(taskNumberInt);
        } else {
            return new DeleteCommand(taskNumberInt);
        }
    }

    /**
     * Parses user input into Todo Command.
     * @param userInputArr User input as array.
     * @param userInput User input.
     * @return Todo Command.
     * @throws DukeException if invalid input.
     */
    private Command parseTodo(String[] userInputArr, String userInput) throws DukeException {
        if (userInputArr.length < 2) {
            throw new DukeException("☹ OOPS!!! "
                    + "The description of a todo cannot be empty.\n");
        }
        String todoName = userInput.substring(5);
        ToDo todo = new ToDo(todoName);
        return new AddCommand(todo);
    }

    /**
     * Parses user input into Deadline Command,
     * @param userInputArr User input as array.
     * @param userInput User input.
     * @return Deadline Command.
     * @throws DukeException if invalid input.
     * @throws ParseException if invalid date.
     */
    private Command parseDeadline(String[] userInputArr, String userInput)
            throws DukeException, ParseException {
        if (userInputArr.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
        String deadlineString = userInput.substring(9);
        String[] deadlineArr = deadlineString.split(DEADLINE_SPLIT);
        if (deadlineArr.length < 2) {
            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.\n");
        }

        Date date = parseDate(deadlineArr);
        boolean isTime = checkIsTime(deadlineArr);

        Deadline deadline = new Deadline(deadlineArr[0], date, isTime);
        return new AddCommand(deadline);
    }

    /**
     * Parses user input into Event Command.
     * @param userInputArr User input as array.
     * @param userInput User input.
     * @return Event Command.
     * @throws DukeException if invalid input.
     * @throws ParseException if invalid date.
     */
    private Command parseEvent(String[] userInputArr, String userInput)
            throws DukeException, ParseException {
        if (userInputArr.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        String eventString = userInput.substring(6);
        String[] eventArr = eventString.split(EVENT_SPLIT);
        if (eventArr.length < 2) {
            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.\n");
        }

        Date dateEvent = parseDate(eventArr);
        boolean isTimeEvent = checkIsTime(eventArr);

        Event event = new Event(eventArr[0], dateEvent, isTimeEvent);
        return new AddCommand(event);
    }

    /**
     * Parses user input into Archive Command.
     * @param userInputArr User input as array.
     * @return Archive Command to archive task.
     * @throws DukeException if invalid user input.
     */
    private Command archiveEvent(String[] userInputArr) throws DukeException {
        if (userInputArr.length < 2) {
            throw new DukeException("Please enter a task number to be archived\n");
        }
        String taskNumber = userInputArr[1];

        int taskNumberInt = Integer.parseInt(taskNumber) - 1;
        return new ArchiveCommand(taskNumberInt);

    }

    /**
     * Check if the array contains a time element.
     * @param arr input that has been partially parsed.
     * @return boolean if array contains time element.
     */
    private boolean checkIsTime(String[] arr) {
        return arr[1].split(SPACE_SPLIT).length != 1;
    }

    /**
     * Parses Date depending on what format of date the user inputs.
     * @param arr input that has been partially parsed.
     * @return Date.
     * @throws ParseException if incorrect format.
     */
    private Date parseDate(String[] arr) throws ParseException {
        if (arr[1].split(SPACE_SPLIT).length == 1) {
            return DATE_FORMAT.parse(arr[1]);
        } else {
            return DATE_TIME_FORMAT.parse(arr[1]);
        }
    }
}
