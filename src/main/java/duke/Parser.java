package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.GuideCommand;
import duke.commands.IntroCommand;
import duke.commands.ListCommand;
import duke.commands.RemindCommand;
import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.DukeDeadlineFormatException;
import duke.exceptions.DukeEmptyActionException;
import duke.exceptions.DukeEmptyDeadlineException;
import duke.exceptions.DukeEmptyEventException;
import duke.exceptions.DukeEmptyFindException;
import duke.exceptions.DukeEmptyToDoException;
import duke.exceptions.DukeEventFormatException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeInvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Parser deals with making sense of the user command.
 */
public class Parser {

    /**
     * Analyses the user input and give an
     * appropriate command.
     *
     * @param commandLine The user input.
     * @return The command based on the user input. The 5 commands are AddCommand, ListCommand,
     * DeleteCommand, DoneCommand and ExitCommand.
     * @throws DukeException Throws exceptions that are all sub class of the DukeException.
     */
    public static Command parse(String commandLine)
            throws DukeException {
        try {
            if (isList(commandLine)) {
                return parseListCommand();
            } else if (isTodo(commandLine)) {
                return parseAddCommandTodo(commandLine);
            } else if (isDeadline(commandLine) || isEvent(commandLine)) {
                return parseAddCommandWithDate(commandLine);
            } else if (isDone(commandLine) || isDelete(commandLine)) {
                return parseDoneDeleteCommand(commandLine);
            } else if (isFind(commandLine)) {
                return parseFindCommand(commandLine);
            } else if (isReminder(commandLine)) {
                return new RemindCommand();
            } else if (isGuide(commandLine)) {
                return new GuideCommand();
            } else if (isBye(commandLine)) {
                return new ExitCommand();
            } else if (isIntro(commandLine)) {
                return new IntroCommand();
            } else {
                throw new DukeInvalidTaskException();
            }
        } catch (DateTimeException e) {
            throw new DukeDateTimeParseException();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Check if the input is 'guide'.
     *
     * @param commandLine The user input.
     * @return Returns a boolean
     */
    private static boolean isGuide(String commandLine) {
        return commandLine.equals("meow");
    }

    /**
     * Check if the input is 'intro'.
     *
     * @param commandLine The user input.
     * @return Returns a boolean
     */
    private static boolean isIntro(String commandLine) {
        return commandLine.equals("intro");
    }

    /**
     * Parses the keyword from the commandline and use it as an argument for
     * creating the FindCommand.
     *
     * @param commandLine The user input.
     * @return FindCommand.
     * @throws DukeEmptyFindException Checks if the keyword is empty.
     */
    private static Command parseFindCommand(String commandLine)
            throws DukeEmptyFindException {
        if (commandLine.equals("todo")) {
            throw new DukeEmptyFindException();
        }
        return new FindCommand(commandLine.substring(5));
    }

    /**
     * Check if the input starts with 'find'.
     *
     * @param commandLine The user input.
     * @return Returns a boolean
     */
    private static boolean isFind(String commandLine) {
        return commandLine.startsWith("find");
    }

    /**
     * Convert a string into a LocalDateTime object.
     *
     * @param commandLine This is part of the user input.
     * @return A LocalDateTime object, based on the user input.
     */
    static LocalDateTime parseDateTime(String commandLine) {
        String[] dateNumbers = commandLine.split("-");

        //Represents 'dd hh:mm'
        String[] dayAndTime = dateNumbers[2].split(" ");

        //Represents 'yyyy'
        int year = Integer.parseInt(dateNumbers[0]);

        //Represents 'MM'
        Integer month = Integer.parseInt(dateNumbers[1]);

        //Represents 'dd'
        Integer day = Integer.parseInt(dayAndTime[0]);

        //Represents 'hh:mm'
        String time = dayAndTime[1];
        String[] timeNumbers = time.split(":");

        //Represents 'hh'
        Integer hour = Integer.parseInt(timeNumbers[0]);

        //Represents 'mm'
        Integer minutes = Integer.parseInt(timeNumbers[1]);

        return LocalDateTime.of(year, month, day, hour, minutes);
    }

    /**
     * Checks if the input is list.
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isList(String string) {
        return string.equals("list");
    }

    /**
     * Checks if the input is bye.
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isBye(String string) {
        return string.equals("bye");
    }

    /**
     * Checks if the input is reminder.
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isReminder(String string) {
        return string.equals("reminder");
    }

    /**
     * Creates the ListCommand.
     *
     * @return ListCommand.
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Checks if the input starts with "todo"
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isTodo(String string) {
        return string.startsWith("todo");
    }

    /**
     * Creates a TaskToDo object and use it as a parameter
     * to create an AddCommand
     *
     * @param commandLine The user input.
     * @return AddCommand.
     * @throws DukeEmptyToDoException Checks if the input includes
     *                                a description.
     */
    private static Command parseAddCommandTodo(String commandLine)
            throws DukeEmptyToDoException {
        if (commandLine.equals("todo")) {
            throw new DukeEmptyToDoException();
        }
        Todo task = new Todo(commandLine.substring(5), false);
        return new AddCommand(task);
    }

    /**
     * Checks if the input starts with "deadline"
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isDeadline(String string) {
        return string.startsWith("deadline");
    }

    /**
     * Checks if the input starts with "event"
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isEvent(String string) {
        return string.startsWith("event");
    }

    /**
     * Check if the commandline does not have any description.
     *
     * @param commandline The user input.
     * @throws DukeEmptyEventException    Checks if the input for event includes a description.
     * @throws DukeEmptyDeadlineException Checks if the input for deadline includes a description.
     */
    private static void checkIfEmptyDescription(String commandline)
            throws DukeEmptyEventException,
            DukeEmptyDeadlineException {
        if (commandline.equals("deadline")) {
            throw new DukeEmptyDeadlineException();
        } else if (commandline.equals("event")) {
            throw new DukeEmptyEventException();
        }
    }

    /**
     * Creates a list of parsed event or deadline.
     *
     * @param commandline The user input.
     * @return A list of parsed event or deadline.
     * @throws DukeDeadlineFormatException Checks if the deadline contains a date and time.
     * @throws DukeEventFormatException    Checks if event contains a date and time.
     * @throws DukeDateTimeParseException  Checks if the data and time is correct.
     */
    private static String[] parseEventOrDeadline(String commandline)
            throws DukeDeadlineFormatException, DukeEventFormatException, DukeDateTimeParseException {
        String[] arrOfString;
        if (isDeadline(commandline)) {
            // Removes the command key 'deadline' and split between description and time and date.
            arrOfString = (commandline.substring(9)).split("/by ", 2);
            if (arrOfString.length == 1) {
                throw new DukeDeadlineFormatException();
            }
        } else {
            // Removes the command key 'event' and split between description and time and date.
            arrOfString = (commandline.substring(6)).split("/at ", 2);
            if (arrOfString.length == 1) {
                throw new DukeEventFormatException();
            }
        }

        if (arrOfString.length != 2) {
            throw new DukeDateTimeParseException();
        }

        return arrOfString;
    }

    /**
     * Creates a Deadline or Event object and use it as a parameter
     * to create an AddCommand.
     *
     * @param commandline The user input.
     * @return AddCommand
     * @throws DukeEmptyDeadlineException  Checks if the input for deadline includes a description.
     * @throws DukeEmptyEventException     Checks if the input for event includes a description.
     * @throws DukeDeadlineFormatException Checks if the deadline contains a date and time.
     * @throws DukeEventFormatException    Checks if event contains a date and time.
     * @throws DukeDateTimeParseException  Checks if the data and time is correct.
     */
    private static Command parseAddCommandWithDate(String commandline)
            throws DukeEmptyDeadlineException,
            DukeEmptyEventException, DukeDeadlineFormatException,
            DukeEventFormatException, DukeDateTimeParseException {
        try {
            checkIfEmptyDescription(commandline);
            String[] arrOfString = parseEventOrDeadline(commandline);

            // Removes extra space at the end
            String description = arrOfString[0].substring(0, arrOfString[0].length() - 1);

            // Format of LocalDateTime is "yyyy-MM-dd HH:mm"
            LocalDateTime dateAndTime = parseDateTime(arrOfString[1]);
            Task task;
            if (isDeadline(commandline)) {
                task = new Deadline(description, false, dateAndTime);
            } else {
                task = new Event(description, false, dateAndTime);
            }
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeDateTimeParseException();
        }
    }

    /**
     * Checks if the input starts with "done"
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isDone(String string) {
        return string.startsWith("done");
    }

    /**
     * Checks if the input starts with "delete"
     *
     * @param string The user input.
     * @return Returns a boolean.
     */
    static boolean isDelete(String string) {
        return string.startsWith("delete");
    }

    /**
     * Creates a Done or Delete Command.
     *
     * @param commandLine The user input.
     * @return Done or Delete Command.
     * @throws DukeEmptyActionException Checks if the number provided is correct.
     */
    private static Command parseDoneDeleteCommand(String commandLine)
            throws DukeEmptyActionException {
        try {
            String[] tokens = commandLine.split(" ");
            // tokens[1] is the index
            int num = Integer.parseInt(tokens[1]);

            if (isDone(commandLine)) {
                return new DoneCommand(num - 1);
            } else {
                return new DeleteCommand(num - 1);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyActionException();
        }
    }
}
