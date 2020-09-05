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
        String[] parsedCommand = command.split(" ", 2);

        if (parsedCommand.length == 1) {
            return checkOneWord(command);
        } else {
            return checkTwoWords(parsedCommand);
        }
    }

    /**
     * Parses one word commands. (e.g. bye, list)
     * @param command The command entered by the user.
     * @return A command
     * @throws DukeException If command is invalid or is missing some details.
     */
    public Command checkOneWord(String command) throws DukeException {
        switch (command) {
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
     * @param parsedCommand The parsed command entered by the user.
     * @return A command.
     * @throws DukeException If the command entered is invalid.
     */
    public Command checkTwoWords(String[] parsedCommand) throws DukeException {
        String task = parsedCommand[0];

        switch (task) {
        case "done":
            if (!isNumeric(parsedCommand[1])) {
                throw new DukeException("Please enter a valid task number to complete!");
            } else {
                int index = Integer.parseInt(parsedCommand[1]) - 1;
                return new DoneCommand(index);
            }

        case "delete":
            if (!isNumeric(parsedCommand[1])) {
                throw new DukeException("Please enter a valid task number for deletion!");
            } else {
                int index = Integer.parseInt(parsedCommand[1]) - 1;
                return new DeleteCommand(index);
            }

        case "check":
            try {
                LocalDate checkedDate = LocalDate.parse(parsedCommand[1]);
                return new CheckCommand(checkedDate);
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
            }

        case "find":
            return new FindCommand(parsedCommand[1]);
        default:
            if (task.equals("todo")) {
                return new TodoCommand(parsedCommand[1]);
            } else if (task.equals("deadline") || task.equals("event")) {
                return checkValidTime(parsedCommand);
            } else {
                throw new DukeException("Please enter a valid task name to add into the list!");
            }

        }
    }

    /**
     * Parses commands involving time-based tasks.
     * @param parsedCommand The parsed command entered by the user.
     * @return A command.
     * @throws DukeException If the date entered is in the wrong format.
     */
    public Command checkValidTime(String[] parsedCommand) throws DukeException {
        String task = parsedCommand[0];
        String[] processTimePrefix = parsedCommand[1].split("/");

        if (processTimePrefix.length == 1) {
            throw new DukeException("You need to include '/by' or '/at' for this task to describe the time.");
        } else {
            String[] time = processTimePrefix[1].split(" ", 2);

            if (time.length == 1) {
                throw new DukeException("The time description cannot be left blank!");
            } else {

                if (task.equals("deadline")) {
                    return new DeadlineCommand(processTimePrefix[0].trim(),
                            time[1].trim());
                } else {
                    assert (task.equals("event")) : "This task name does not exist!";
                    return new EventCommand(processTimePrefix[0].trim(),
                            time[1].trim());
                }
            }
        }
    }

}
