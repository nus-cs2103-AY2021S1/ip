package duke.parser;

import duke.commands.*;
import duke.enums.CommandEnum;
import duke.exceptions.DukeException;
import duke.ui.Ui;

/**
 * Encapsulates a parser that interprets user input, and decides what to do with the given input.
 */
public class Parser {
    /**
     * Instance of UI class that helps display messages to the user.
     */
    private Ui ui;

    /**
     * Constructs a new Parser object.
     */
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Parses commands by the user.
     *
     * @param command Input given by the user.
     * @return Relevant command to execute after input is parsed.
     * @throws DukeException Exceptions from parsing of user input.
     */
    public static Command parse (String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        CommandEnum commandEnum = CommandEnum.stringToEnum(splitCommand[0]);
        switch (commandEnum) {
        case LIST: {
            return parseList();
        }
        case BYE: {
            return parseBye();
        }
        case DONE: {
            int indexOfTaskToBeDone = Integer.parseInt(splitCommand[1]);
            return parseDone(indexOfTaskToBeDone);
        }
        case DEADLINE: {
            return parseDeadline(splitCommand[1]);
        }
        case EVENT: {
            return parseEvent(splitCommand[1]);
        }
        case TODO: {
            try {
                if (splitCommand.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                } else {
                    return parseTodo(splitCommand[1]);
                }
            } catch (DukeException error) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty");
            }
        }
        case DELETE: {
            int indexOfTaskToBeDeleted = Integer.parseInt(splitCommand[1]);
            return parseDelete(indexOfTaskToBeDeleted);
        }
        case FIND : {
            return parseFind(splitCommand[1]);
        }
        case RESCHEDULE: {
            String[] indexAndDate = splitCommand[1].split(" ", 2);
            int indexOfTaskToReschedule = Integer.parseInt(indexAndDate[0]);
            return parseReschedule(indexOfTaskToReschedule, indexAndDate[1]);
        }
        default: {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        }
    }

    /**
     * Called when the user wants a List command.
     *
     * @return ListCommand to based on user request.
     */
    private static ListCommand parseList() {
        return new ListCommand();
    }

    /**
     * Called when the user wants a List command.
     *
     * @return ListCommand based on user request.
     */
    public static ByeCommand parseBye() {
        return new ByeCommand();
    }

    /**
     * Called when the user wants a Done command.
     *
     * @param indexOfTaskToBeDone Index of the task in the todoList that needs to be marked as done.
     * @return DoneCommand based on user request.
     */
    public static DoneCommand parseDone(int indexOfTaskToBeDone) {
        return new DoneCommand(indexOfTaskToBeDone);
    }

    /**
     * Called when the user wants a Deadline command.
     *
     * @param details Information about the Deadline task, without the Deadline command word.
     * @return DeadlineCommand based on user request.
     */
    public static DeadlineCommand parseDeadline(String details) {
        String[] deadlineTaskDetails = details.split(" /by ");
        return new DeadlineCommand(deadlineTaskDetails);
    }

    /**
     * Called when the user wants an Event command.
     *
     * @param details Information about the Event task, without the Event command word.
     * @return EventCommand based on user request.
     */
    public static EventCommand parseEvent(String details) {
        String[] eventTaskDetails = details.split(" /at ");
        return new EventCommand(eventTaskDetails);
    }

    /**
     * Called when the user wants a Todo command.
     *
     * @param details Information about the Todo task, without the Todo command word.
     * @return TodoCommand based on user request.
     */
    public static TodoCommand parseTodo(String details) {
        return new TodoCommand(details);
    }

    /**
     * Called when the user wants a Delete command.
     *
     * @param indexOfTaskToBeDeleted Index of the task in the todoList that needs to be deleted.
     * @return DeleteCommand based on user request.
     */
    public static DeleteCommand parseDelete(int indexOfTaskToBeDeleted) {
        return new DeleteCommand(indexOfTaskToBeDeleted);
    }

    /**
     * Called when the user wants a Find command.
     *
     * @param keyword The target keyword to search for relevant tasks.
     * @return FindCommand based on user request.
     */
    public static FindCommand parseFind(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Called when the user wants a Reschedule command.
     *
     * @param indexOfTaskToReschedule Index in the todoList of the target task to reschedule.
     * @param newDateAndTime Updated date and tome of the task.
     * @return RescheduleCommand based on user request.
     */
    public static RescheduleCommand parseReschedule(int indexOfTaskToReschedule, String newDateAndTime) {
        return new RescheduleCommand(indexOfTaskToReschedule, newDateAndTime);
    }
}
