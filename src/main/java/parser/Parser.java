package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tasklist.TaskList;
import storage.Storage;

import duke.DukeException;

import task.Deadline;
import task.Event;

/**
 * Parser parses the commands inputted by the user into the Java Duke Program
 * and calls the necessary functions in the task list.
 *
 * @author (Sruthi)
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
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
        String[] instructions = input.split(" ");
        String command = instructions[0];
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
        int firstHour = (int) time.charAt(0) - '0';
        int secondHour = (int) time.charAt(1) - '0';
        int hours = firstHour * 10 + secondHour;
        String amPM;
        String convertedTime;
        if (hours < 12) {
            amPM = "am";
        } else {
            amPM = "pm";
        }
        hours = hours % 12;
        if (hours == 0) {
            convertedTime = "12";
        } else {
            convertedTime = Integer.toString(hours);
        }
        return convertedTime + "." + time.substring(2, 4) + amPM;
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
        String[] splitItem = item.split("/at ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a time/date the event is held at.");
        }
        LocalDate dueDate = Parser.formatDate(splitItem[1]);
        String dateTime = Parser.formatDateString(dueDate, splitItem[1]);
        return new Event(splitItem[0], dateTime, dueDate, splitItem[1], isCompleted);
    }
}
