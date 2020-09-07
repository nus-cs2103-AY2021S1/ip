package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/** This class deals with making sense of user commands. */
public class Parser {
    /**
     * @param command The command input from the user.
     * @return The Command to be executed.
     * @throws DukeException when the user input command cannot be parsed.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.startsWith("list")) {
            return parseListCommand(command);
        } else if (command.startsWith("find")) {
            int findCommandBreak = 5;
            String keyword = command.substring(findCommandBreak);
            return new FindCommand(keyword);
        } else if (command.startsWith("done ")) {
            int doneCommandBreak = 5;
            int i = Integer.valueOf(command.substring(doneCommandBreak));
            return new DoneCommand(i);
        } else if (command.startsWith("delete ")) {
            int deleteCommandBreak = 7;
            int i = Integer.valueOf(command.substring(deleteCommandBreak));
            return new DeleteCommand(i);
        } else if (command.startsWith("todo ")) {
            int todoCommandBreak = 5;
            ToDo todo = new ToDo(command.substring(todoCommandBreak));
            return new AddCommand(todo);
        } else if (command.startsWith("deadline ")) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith("event ")) {
            return parseEventCommand(command);
        } else {
            throw new DukeException("Sorry I don't understand!");
        }
    }

    /**
     * @param command User command that starts with list.
     * @return The ListCommand to be executed.
     * @throws DukeException The user input command cannot be parsed.
     */
    public static ListCommand parseListCommand(String command) throws DukeException {
        String[] splits = command.split(" ");
        if (command.equals("list")) {
            return new ListCommand();
        } else if (splits[0].equals("list") && splits.length == 2) {
            LocalDate date = LocalDate.parse(splits[1]);
            return new ListCommand(date);
        } else {
            throw new DukeException("Sorry I don't understand");
        }
    }

    /**
     * @param command User input command that starts with deadline.
     * @return The AddCommand to be executed.
     * @throws DukeException The user input command cannot be parsed.
     */
    public static AddCommand parseDeadlineCommand(String command) throws DukeException {
        int indexOfBy = command.indexOf(" /by ");
        if (indexOfBy >= 9) {
            int indexOfTitle = 9;
            int indexOfDeadline = indexOfBy + 5;
            String desc = command.substring(indexOfTitle, indexOfBy);
            String by = command.substring(indexOfDeadline);

            String[] dateAndTime = by.split(" ");
            String dateString = dateAndTime[0];
            LocalDate date = LocalDate.parse(dateString);

            Deadline deadline = null;
            if (dateAndTime.length == 2) {
                String timeString = dateAndTime[1];
                LocalTime time = LocalTime.parse(timeString);
                deadline = new Deadline(desc, date, time);
            } else {
                deadline = new Deadline(desc, date);
            }
            return new AddCommand(deadline);
        } else {
            throw new DukeException("Invalid deadline input");
        }
    }

    /**
     * @param command User input command that starts with event.
     * @return The AddCommand to be executed.
     * @throws DukeException The user input command cannot be parsed.
     */
    public static AddCommand parseEventCommand(String command) throws DukeException {
        int indexOfAt = command.indexOf(" /at ");
        try {
            if (indexOfAt < 9) {
                throw new DukeException("Invalid event input");
            }

            int indexOfTitle = 6;
            int indexOfDate = indexOfAt + 5;
            String desc = command.substring(indexOfTitle, indexOfAt);
            String at = command.substring(indexOfDate);

            String[] dateAndTime = at.split(" ");
            String dateString = dateAndTime[0];
            LocalDate date = LocalDate.parse(dateString);

            Event event = null;
            if (dateAndTime.length == 2) {
                String timeRangeString = dateAndTime[1];
                String[] startAndEndTime = timeRangeString.split("-");

                String startTimeString = startAndEndTime[0];
                LocalTime startTime = LocalTime.parse(startTimeString);

                if (startAndEndTime.length == 2) {
                    String endTimeString = startAndEndTime[1];
                    event = new Event(desc, date, startTime, LocalTime.parse(endTimeString));
                } else {
                    event = new Event(desc, date, startTime);
                }
            } else {
                event = new Event(desc, date);
            }

            return new AddCommand(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid event input");
        }
    }
}
