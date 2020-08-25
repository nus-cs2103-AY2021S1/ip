package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides a class to interprets user inputs and converts them into Commands
 * to be executed.
 */
public class Parser {

    /** Represents date and time. */
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    /** Represents date. */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

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
        String[] userInputArr = userInput.split(" ");
        String userKeyword = userInputArr[0];
        try {
            switch (userKeyword) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "done":
            case "delete":
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


            case "todo":
                if (userInputArr.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                String todoName = userInput.substring(5);
                ToDo todo = new ToDo(todoName);
                return new AddCommand(todo);

            case "deadline":
                if (userInput.length() < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
                }
                String deadlineString = userInput.substring(9);
                String[] deadlineArr = deadlineString.split(" /by ");
                if (deadlineArr.length < 2) {
                    throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.\n");
                }
                Date date;
                boolean isTime;

                if (deadlineArr[1].split(" ").length == 1) {
                    date = DATE_FORMAT.parse(deadlineArr[1]);
                    isTime = false;
                } else {
                    date = DATE_TIME_FORMAT.parse(deadlineArr[1]);
                    isTime = true;
                }


                Deadline deadline = new Deadline(deadlineArr[0], date, isTime);
                return new AddCommand(deadline);

            case "event":
                if (userInput.length() < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
                }
                String eventString = userInput.substring(6);
                String[] eventArr = eventString.split(" /at ");
                if (eventArr.length < 2) {
                    throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.\n");
                }
                Date dateEvent;
                boolean isTimeEvent;

                if (eventArr[1].split(" ").length == 1) {
                    dateEvent = DATE_FORMAT.parse(eventArr[1]);
                    isTimeEvent = false;
                } else {
                    dateEvent = DATE_TIME_FORMAT.parse(eventArr[1]);
                    isTimeEvent = true;
                }

                Event event = new Event(eventArr[0], dateEvent, isTimeEvent);
                return new AddCommand(event);

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }


        } catch (ParseException e) {
            throw new DukeException("Please enter a date and time in the format of \n"
                    + "dd/MM/2020 HHmm (e.g. 02/12/2020 1530) "
                    + "or dd/MM/2020 (e.g. 15/02/2020)\n");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a task number in numeric format\n");
        }
    }

}
