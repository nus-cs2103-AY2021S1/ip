package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Parser() {
    }

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
                    date = dateFormat.parse(deadlineArr[1]);
                    isTime = false;
                } else {
                    date = dateTimeFormat.parse(deadlineArr[1]);
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
                    dateEvent = dateFormat.parse(eventArr[1]);
                    isTimeEvent = false;
                } else {
                    dateEvent = dateTimeFormat.parse(eventArr[1]);
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
