package parser;

import duke.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser parses the commands inputted by the user into the Java Duke Program
 * and calls the necessary functions in the task list.
 *
 * @author (Sruthi)
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private String prevCommand = "";
    /**
     * Has a taskList and storage object to call the appropriate taskList functions and
     * to overwrite the file containing the tasks at the end of the Java Duke Program
     *
     * @param taskList list of tasks
     * @param storage storage that stores all the tasks
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Takes in the user input and calls the appropriate taskList functions based on
     * the commands in the input. It then returns the String that needs to be printed
     * to the user, back to the UI. It throws Duke Exception if any error occurred
     * parsing and carrying out the commands.
     *
     * @param input input given by the user
     * @return String to be printed
     * @throws DukeException
     */
    public String scenarios(String input) throws DukeException {
        assert(input.contains(" "));
        String[] instructions = input.split(" ");
        String command = instructions[0];
        if (!command.equals("undo")) {
            prevCommand = input;
        }
        switch (command) {
        case "bye":
            storage.overwriteTodoList();
            return command;
        case "list":
            return taskList.showList();
        case "done":
            return taskList.completeItem(input);
        case "delete":
            return taskList.deleteItem(input);
        case "find":
            return taskList.findItem(input);
        case "todo":
            String todo = input.substring(4);
            return taskList.addItem(command, todo);
        case "deadline":
            String deadline = input.substring(8);
            return taskList.addItem(command, deadline);
        case "event": {
            String event = input.substring(5);
            return taskList.addItem(command, event);
        }
        case "undo":
            return undoPrevCommand();
        case "LanZhan":
            return "Wei Ying, \n\n"
                    + "Why am I sitting here having such a useless conversation with you?\n\n Go away!";
        default:
            throw new DukeException("Oops! I'm sorry but I have no idea what that means >.<");
        }
    }

    /**
     * Takes in the String of the date and time inputted by the user and converts it to
     * a LocalDate object which is then returned. It throws a DukeException if any error
     * occurred while formatting.
     *
     * @param dateTime the date and time string inputted by the user
     * @return LocalDate duedate of the task
     * @throws DukeException
     */
    public static LocalDate formatDate(String dateTime) throws DukeException {
        try {
            assert(dateTime.contains(" "));
            String[] split = dateTime.split(" ");
            String[] dayMonthYear = split[0].split("/");
            String date = dayMonthYear[2] + "-" + dayMonthYear[1] + "-";
            if (dayMonthYear[0].length() == 1) {
                date += "0" + dayMonthYear[0];
            } else {
                date += dayMonthYear[0];
            }
            return LocalDate.parse(date);
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YYYY 2359");
        }
    }

    /**
     * Takes in a LocalDate object and a String containing the date and time inputted by the user
     * and then parses it to the correct format String needed for printing and returns it. It
     * throws a DukeException if any error occurred while parsing.
     *
     * @param dateTime the date and time string inputted by the user
     * @param dueDate the date the task is due at
     * @return String to be printed for the date and time of the task due
     * @throws DukeException
     */
    public static String formatDateString(LocalDate dateTime, String dueDate) throws DukeException {
        try {
            String time = "2359";
            String[] split = dueDate.split(" ");
            if (split.length == 2) {
                time = split[1];
            }
            String format = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return format + " " + formatTime(time);
        } catch (Exception e) {
            throw new DukeException("Incorrect format of date. It should be: DD/MM/YY time");
        }
    }

    /**
     * Takes in a String containing the time inputted by the user and parses it to the correct
     * format needed for printing to the User and returns it. It throws a DukeException if any
     * error occurred while parsing.
     *
     * @param time the time string inputted by the user
     * @return String of the time that the task is due
     */
    public static String formatTime(String time) {
        assert((int) time.charAt(0) > '0');
        assert((int) time.charAt(1) > '0');
        int firstHour = (int) time.charAt(0) - '0';
        int secondHour = (int) time.charAt(1) - '0';
        int hours = firstHour * 10 + secondHour;
        String amPM = getAmPm(hours);
        hours = hours % 12;
        String convertedTime = convertHour(hours);
        return convertedTime + "." + time.substring(2, 4) + amPM;
    }

    /**
     * Based on the hour given, determines whether it's morning or afternoon
     * and returns the appropriate AM or PM String.
     *
     * @param hours
     * @return String of whether it's am or pm
     */
    public static String getAmPm(int hours) {
        if (hours < 12) {
            return "am";
        } else {
            return "pm";
        }
    }

    /**
     * Converts 24 hour time to 12 hour time
     *
     * @param hours
     * @return String of the hour part of the time
     */
    public static String convertHour(int hours) {
        hours = hours % 12;
        if (hours == 0) {
            return "12";
        } else {
            return Integer.toString(hours);
        }
    }

    /**
     * Takes in the input from the user and a boolean of whether the task is completed or not
     * and parses the input before creating the Deadline object and returning it. It throws a
     * DukeException if any error occurred while parsing the input.
     *
     * @param item the description of the task
     * @param isCompleted whether the task is completed
     * @return Deadline the task
     * @throws DukeException
     */
    public static Deadline parseDeadline(String item, boolean isCompleted) throws DukeException {
        assert(item.contains("/by "));
        String[] splitItem = item.split("/by ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a deadline to finish task by.");
        }
        LocalDate dueDate = Parser.formatDate(splitItem[1]);
        String dateTime = Parser.formatDateString(dueDate, splitItem[1]);
        return new Deadline(splitItem[0], dateTime, dueDate, splitItem[1], isCompleted);
    }

    /**
     * Takes in the input from the user and a boolean of whether the task is completed or not
     * and parses the input before creating the Event object and returning it. It throws a
     * DukeException if any error occurred while parsing the input.
     *
     * @param item the description of the task
     * @param isCompleted whether the task is completed
     * @return Event the task
     * @throws DukeException
     */
    public static Event parseEvent(String item, boolean isCompleted) throws DukeException {
        assert(item.contains("/at "));
        String[] splitItem = item.split("/at ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a time/date the event is held at.");
        }
        LocalDate dueDate = Parser.formatDate(splitItem[1]);
        String dateTime = Parser.formatDateString(dueDate, splitItem[1]);
        return new Event(splitItem[0], dateTime, dueDate, splitItem[1], isCompleted);
    }

    /**
     * Undoes the previous command inputted by the user
     *
     * @return String to be outputted to the user
     * @throws DukeException
     */
    private String undoPrevCommand() throws DukeException {
        if (prevCommand.length() == 0) {
            throw new DukeException("Nothing to undo >.<");
        }
        String[] instructions = prevCommand.split(" ");
        String command = instructions[0];
        String oldCommand = prevCommand;
        return undoCommand(command, oldCommand);
    }

    /**
     * Undoes the previous command inputted by the user
     *
     * @param command
     * @param oldCommand
     * @return String to be inputted to the user
     * @throws DukeException
     */
    private String undoCommand(String command, String oldCommand) throws DukeException {
        prevCommand = "undo " + oldCommand;
        switch (command) {
        case "bye":
            storage.overwriteTodoList();
            return command;
        case "list":
            return taskList.showList();
        case "done":
            return taskList.unCompleteItem(oldCommand);
        case "delete":
            return taskList.unDeleteItem(oldCommand);
        case "find":
            return taskList.findItem(oldCommand);
        case "todo":
        case "deadline":
        case "event":
            return taskList.deleteLastAddedItem();
        case "undo":
            return scenarios(oldCommand.substring(5));
        case "LanZhan":
            return "Wei Ying, \n\n"
                    + "Why am I sitting here having such a useless conversation with you?\n\n Go away!";
        default:
            throw new DukeException("Oops! Previous command could not be undone");
        }
    }
}
