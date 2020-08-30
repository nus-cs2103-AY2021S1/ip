package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

/**
 * Parses the string commands passed into the program by the user through
 * the console, and afterward processes them to create Command objects.
 */
public class Parser {

    /**
     * Checks if a given String is a number
     * @param test The String to test
     * @return A boolean confirming if the String is a number
     */
    public boolean isNumeric(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Parses the input given by the user into the program.
     * @param command The command entered by the user.
     * @return A new Command object.
     * @throws DukeException If command is invalid or missing details.
     */
    public Command parse(String command) throws DukeException {
        String[] modEcho = command.split(" ", 2);

        if (modEcho.length == 1) {
            return checkOneWord(command);
        } else {
            return checkTwoWords(modEcho);
        }
    }

    /**
     * Parses one word commands. (e.g. bye, list)
     * @param echo The command entered by the user.
     * @return A command
     * @throws DukeException If command is invalid or is missing some details.
     */
    public Command checkOneWord(String echo) throws DukeException {
        switch (echo) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            throw new DukeException("Please enter a valid"
                    + " description for your task!");
        case "done":
            throw new DukeException("Please enter the ID "
                    + "of the task you would like to complete.");
        case "delete":
            throw new DukeException("Please retry and enter "
                    + "the ID of the task to be deleted.");
        case "check":
            throw new DukeException("Please enter a date to check!");
        case "find":
            throw new DukeException("Please enter a keyword to search for.");
        default:
            throw new DukeException("Please enter a valid "
                    + "command into the console.");
        }
    }

    /**
     * Parses two word commands. (e.g. done 2, delete 3)
     * @param modEcho The command entered by the user.
     * @return A command.
     * @throws DukeException If the command entered is invalid.
     */
    public Command checkTwoWords(String[] modEcho) throws DukeException {
        String task = modEcho[0];

        switch (task) {
        case "done":
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number to complete!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;
                return new DoneCommand(index);
            }

        case "delete":
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number for deletion!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;
                return new DeleteCommand(index);
            }

        case "check":
            try {
                LocalDate checkedDate = LocalDate.parse(modEcho[1]);
                return new CheckCommand(checkedDate);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
            }

        case "find":
            return new FindCommand(modEcho[1]);
        default:
            if (task.equals("todo")) {
                return new TodoCommand(modEcho[1]);
            } else if (task.equals("deadline") || task.equals("event")) {
                return checkValidTime(modEcho);
            } else {
                throw new DukeException("Please enter a valid task name to add into the list!");
            }

        }
    }

    /**
     * Parses commands involving time-based tasks.
     * @param modEcho The command entered by the user.
     * @return A command.
     * @throws DukeException If the date entered is in the wrong format.
     */
    public Command checkValidTime(String[] modEcho) throws DukeException {
        String task = modEcho[0];
        String[] processTime = modEcho[1].split("/");

        if (processTime.length == 1) {
            throw new DukeException("You need to include '/by' or '/at' for this task to describe the time.");
        } else {
            String[] time = processTime[1].split(" ", 2);

            if (time.length == 1) {
                throw new DukeException("The time description cannot be left blank!");
            } else {

                if (task.equals("deadline")) {
                    return new DeadlineCommand(processTime[0].trim(),
                            time[1].trim());
                } else {
                    return new EventCommand(processTime[0].trim(),
                            time[1].trim());
                }
            }
        }
    }

}
