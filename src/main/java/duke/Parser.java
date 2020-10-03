package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.command.UpdateDateCommand;
import duke.command.UpdateDescCommand;

import duke.exception.DukeEmptyInputException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidDateTimeException;
import duke.exception.DukeInvalidKeywordException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Responsible for parsing user inputs.
 */
public class Parser {

    /**
     * Parses the user input and returns a Command based on the input.
     *
     * @param fullCommand User input.
     * @throws DukeEmptyInputException If input is empty.
     * @throws DukeInvalidDateTimeException If date and time input is invalid.
     * @throws DukeInvalidCommandException If command is invalid.
     * @throws DukeInvalidKeywordException If keyword is invalid.
     */
    public static Command parse(String fullCommand) throws DukeEmptyInputException, DukeInvalidDateTimeException,
            DukeInvalidCommandException, DukeInvalidKeywordException {
        String[] commands = fullCommand.trim().split(" ", 2);
        switch(commands[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(commands[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(commands[1]));
        case "update":
            return parseUpdate(commands[1]);
        case "find":
            if (commands.length < 2) {
                throw new DukeInvalidKeywordException();
            }
            return parseFind(commands[1]);
        case "todo":
            if (commands.length < 2) {
                throw new DukeEmptyInputException("The description of a todo cannot be empty.");
            }
            return parseToDo(commands[1]);
        case "deadline":
            if (commands.length < 2) {
                throw new DukeEmptyInputException("The description of a deadline cannot be empty.");
            }
            return parseDeadline(commands[1]);
        case "event":
            if (commands.length < 2) {
                throw new DukeEmptyInputException("The description of an event cannot be empty.");
            }
            return parseEvent(commands[1]);
        default:
            throw new DukeInvalidCommandException("Unknown command.");
        }
    }


    /**
     * Parses a UpdateCommand.
     *
     * @param command Command given by user.
     * @return Returns a UpdateCommand associated with the task to be updated.
     */
    static UpdateCommand parseUpdate(String command) throws DukeEmptyInputException, DukeInvalidDateTimeException {
        String[] details;
        try {
            if (command.contains(" /date")) {
                details = command.split(" /date ");
                if (details.length < 2) {
                    throw new DukeEmptyInputException("You have not entered a new date for your task!");
                }
                int taskNo = Integer.parseInt(details[0]);
                return new UpdateDateCommand(taskNo, details[1]);
            } else {
                details = command.split(" /desc ");
                if (details.length < 2) {
                    throw new DukeEmptyInputException("You have not entered a new description for your task!");
                }
                int taskNo = Integer.parseInt(details[0]);
                return new UpdateDescCommand(taskNo, details[1]);
            }
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("The new date for your task cannot be parsed.");
        }
    }

    /**
     * Parses an FindCommand associated with a keyword.
     *
     * @param keyword Search keyword.
     * @return Returns an FindCommand associated with the search keyword.
     */
    static FindCommand parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Parses an AddCommand associated with a Todo.
     *
     * @param description Description of the Todo.
     * @return Returns an AddCommand associated with the Todo to be added.
     * @throws DukeEmptyInputException If input is empty.
     */
    static AddCommand parseToDo(String description) throws DukeEmptyInputException {
        if (description.length() == 0) {
            throw new DukeEmptyInputException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses an AddCommand associated with a Deadline task.
     *
     * @param description Description of the Deadline task.
     * @return Returns an AddCommand associated with the Deadline task to be added.
     * @throws DukeEmptyInputException If input is empty.
     * @throws DukeInvalidDateTimeException If date and time input is invalid.
     */
    static AddCommand parseDeadline(String description) throws DukeEmptyInputException, DukeInvalidDateTimeException {
        String[] details = description.split(" /by ");
        if (details[0].length() == 0) {
            throw new DukeEmptyInputException("The description of a deadline cannot be empty.");
        }
        if (details.length <= 1 || details[1].length() == 0) {
            throw new DukeEmptyInputException("The due date of a deadline cannot be empty.");
        }
        try {
            return new AddCommand(new Deadline(details[0], details[1]));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("The date for your deadline cannot be parsed.");
        }
    }

    /**
     * Parses an AddCommand associated with an Event task.
     *
     * @param description Description of the Event task.
     * @return Returns an AddCommand associated with the Event task to be added.
     * @throws DukeEmptyInputException If input is empty.
     * @throws DukeInvalidDateTimeException If date and time input is invalid.
     */
    static AddCommand parseEvent(String description) throws DukeEmptyInputException, DukeInvalidDateTimeException {
        String[] details = description.split(" /at ");
        if (details[0].length() == 0) {
            throw new DukeEmptyInputException("The description of a event cannot be empty.");
        }
        if (details.length <= 1 || details[1].length() == 0) {
            throw new DukeEmptyInputException("The date of an event cannot be empty.");
        }
        try {
            return new AddCommand(new Event(details[0], details[1]));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("The date for your event cannot be parsed.");
        }
    }

}
