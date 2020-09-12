package duke.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.DisplayListCommand;
import duke.command.DoneTaskCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.SaveCommand;
import duke.command.SortCommand;
import duke.error.DukeError;
import duke.error.EmptyDateError;
import duke.error.EmptyTaskError;
import duke.error.FindMissingError;
import duke.error.InvalidDateInputError;
import duke.error.UnknownCommandError;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The class that parses the user's input into commands that Duke can understand.
 */
public abstract class Parser {
    /**
     * Reads the user's input and returns a command for Duke to execute.
     *
     * @param inputLine The user's input
     * @return Returns an executable command based on the user's input
     */
    public static Command parse(String inputLine) {
        String[] arr = inputLine.split(" ");

        if (inputLine.equals("bye")) {
            return new ByeCommand();
        } else if (inputLine.equals("help")) {
            return new HelpCommand();
        } else if (inputLine.equals("list")) {
            return new DisplayListCommand();
        } else if (inputLine.equals("save")) {
            return new SaveCommand();
        } else if (inputLine.equals("sort")) {
            // can sort by task desc
            return new SortCommand();
        } else if (arr[0].equals("find")) {
            try {
                if (arr.length == 1) {
                    throw new FindMissingError();
                }
            } catch (DukeError e) {
                return new ErrorCommand(e);
            }
            return new FindCommand(inputLine.substring(5));
        } else if (arr.length == 2 && (arr[0].equals("done") || arr[0].equals("delete")) && isInteger(arr[1])) {
            int num = Integer.parseInt(arr[1]);
            if (arr[0].equals("done")) {
                return new DoneTaskCommand(num);
            } else if (arr[0].equals("delete")) {
                return new DeleteTaskCommand(num);
            }
        } else {
            try {
                switch (arr[0]) {
                case "todo":
                    return addTodo(inputLine);

                case "deadline":
                    return addDeadline(inputLine);

                case "event":
                    return addEvent(inputLine);

                default:
                    throw new UnknownCommandError();
                }
            } catch (DukeError e) {
                return new ErrorCommand(e);
            } catch (DateTimeParseException e) {
                return new ErrorCommand(new InvalidDateInputError());
            }
        }
        return new ErrorCommand(new UnknownCommandError());
    }

    private static Command addTodo(String inputLine) throws EmptyTaskError {
        String todo = inputLine.substring(4).trim();
        if (todo.equals("")) {
            throw new EmptyTaskError();
        } else {
            return new AddTaskCommand(new ToDo(todo));
        }
    }

    private static Command addDeadline(String inputLine) throws EmptyTaskError, EmptyDateError, InvalidDateInputError {
        int by = inputLine.indexOf("/by");
        String deadline;
        String byDate;
        if (by == -1) {
            deadline = inputLine.substring(8).trim();
            byDate = "";
        } else {
            deadline = inputLine.substring(8, by).trim();
            byDate = inputLine.substring(by + 3).trim();
        }
        if (deadline.equals("")) {
            throw new EmptyTaskError();
        } else if (byDate.equals("")) {
            throw new EmptyDateError();
        } else {
            return new AddTaskCommand(new Deadline(deadline, formatDateTime(byDate)));
        }
    }

    private static Command addEvent(String inputLine) throws EmptyTaskError, EmptyDateError, InvalidDateInputError {
        int at = inputLine.indexOf("/at");
        String event;
        String atDate;
        if (at == -1) {
            event = inputLine.substring(5).trim();
            atDate = "";
        } else {
            event = inputLine.substring(5, at).trim();
            atDate = inputLine.substring(at + 3).trim();
        }
        if (event.equals("")) {
            throw new EmptyTaskError();
        } else if (atDate.equals("")) {
            throw new EmptyDateError();
        } else {
            return new AddTaskCommand(new Event(event, formatDateTime(atDate)));
        }
    }

    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    private static LocalDateTime formatDateTime(String s) throws InvalidDateInputError {
        String[] dateAndTime = s.split(" ");

        if (dateAndTime.length != 2) {
            throw new InvalidDateInputError();
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String beforeDate = dateAndTime[0];
        String[] arrDate = beforeDate.split("/");
        if (arrDate.length != 3) {
            throw new InvalidDateInputError();
        }

        if (arrDate[0].length() == 1) {
            beforeDate = "0".concat(beforeDate);
        }
        if (arrDate[1].length() == 1) {
            beforeDate = String.format("%s/0%s/%s", beforeDate.substring(0, 2),
                    arrDate[1], arrDate[2]);
        }

        String beforeTime = dateAndTime[1];
        if (beforeTime.length() == 4) {
            beforeTime = beforeTime.substring(0, 2) + ":" + beforeTime.substring(2);
        }
        String after = beforeDate + " " + beforeTime;
        return LocalDateTime.parse(after, dateFormatter);
    }

}
