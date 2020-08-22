package duke;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.*;
import duke.exception.*;
import duke.task.*;

/**
 * Responsible for parsing user inputs.
 */
public class Parser {

    /**
     * Parses the user input and returns a Command based on the input.
     * @param fullCommand User input.
     * @throws DukeEmptyInputException If input is empty.
     * @throws DukeInvalidDateTimeException If date and time input is invalid.
     * @throws DukeInvalidCommandException If command is invalid.
     */
    static Command parse(String fullCommand) throws DukeEmptyInputException, DukeInvalidDateTimeException, DukeInvalidCommandException, DukeInvalidKeywordException {
        String[] commandArr = fullCommand.trim().split(" ", 2);
        switch(commandArr[0]) {
            case "bye":
                return parseBye();
            case "list":
                return parseList();
            case "done":
                return parseDone(Integer.parseInt(commandArr[1]));
            case "delete":
                return parseDelete(Integer.parseInt(commandArr[1]));
            case "find":
                if(commandArr.length < 2) {
                    throw new DukeInvalidKeywordException();
                }
                return parseFind(commandArr[1]);
            case "todo":
                if(commandArr.length < 2) {
                    throw new DukeEmptyInputException("The description of a todo cannot be empty.");
                }
                return parseToDo(commandArr[1]);
            case "deadline":
                if(commandArr.length < 2) {
                    throw new DukeEmptyInputException("The description of a todo cannot be empty.");
                }
                return parseDeadline(commandArr[1]);
            case "event":
                if(commandArr.length < 2) {
                    throw new DukeEmptyInputException("The description of a todo cannot be empty.");
                }
                return parseEvent(commandArr[1]);
            default:
                throw new DukeInvalidCommandException("Unknown command.");
        }
    }

    /**
     * Parses a ByeCommand
     * @return Returns a ByeCommand.
     */
    static ByeCommand parseBye() {
        return new ByeCommand();
    }

    /**
     * Parses a ListCommand
     * @return Returns a ListCommand.
     */
    static ListCommand parseList() {
        return new ListCommand();
    }

    /**
     * Parses a DoneCommand
     * @param taskNo Number of the task that is to be completed.
     * @return Returns a DoneCommand associated with the task to be completed.
     */
    static DoneCommand parseDone(int taskNo) {
        return new DoneCommand(taskNo);
    }

    /**
     * Parses a DeleteCommand
     * @param taskNo Number of the task that is to be deleted.
     * @return Returns a DeleteCommand associated with the task to be deleted.
     */
    static DeleteCommand parseDelete(int taskNo) {
        return new DeleteCommand(taskNo);
    }

    /**
     * Parses an FindCommand associated with a keyword.
     * @param keyword Search keyword.
     * @return Returns an FindCommand associated with the search keyword.
     */
    static FindCommand parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Parses an AddCommand associated with a Todo.
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
