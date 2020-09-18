package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DeleteTaskCommand;
import duke.command.DoneCommand;
import duke.command.DoneTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FindTaskCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.command.PriorityLevelCommand;
import duke.command.PrioritySetCommand;
import duke.command.ResetCommand;
import duke.command.TaskCommand;
import duke.command.TaskTypeCommand;
import duke.exception.DukeException;
import duke.exception.InvalidPriorityLevelException;
import duke.exception.InvalidTaskTypeException;
import duke.exception.UnknownCommandException;
import duke.exception.WrongFormatException;
import duke.task.PriorityLevel;
import duke.task.TaskType;

/**
 * Represents the parser that processes user input.
 */
public class Parser {

    /**
     * Previous command that was executed before the current user input was given.
     */
    protected static Command prevCommand = new ResetCommand();

    /**
     * Returns the command that handles the given input.
     * @param input Input by the user.
     * @return Command that handles the input.
     * @throws DukeException For when any of the command executions encounter an exception.
     */
    public static Command parse(String input) throws DukeException {
        Command command;
        if (Parser.prevCommand.getCommandType().equals(CommandType.ADD)) {
            command = parseTaskType(input);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.TASKTYPE)) {
            command = parseTask(input, prevCommand);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.DELETE)) {
            command = parseDelete(input);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.DONE)) {
            command = parseDone(input);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.FIND)) {
            command = parseFind(input);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.PRIORITY)) {
            command = parsePriority(input);
        } else if (Parser.prevCommand.getCommandType().equals(CommandType.PRIORITYLEVEL)) {
            command = parsePriorityLevel(input, prevCommand);
        } else {
            switch (input.toLowerCase()) {
            case "help":
                command = new HelpCommand();
                break;
            case "add":
                command = new AddCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                command = new DoneCommand();
                break;
            case "delete":
                command = new DeleteCommand();
                break;
            case "find":
                command = new FindCommand();
                break;
            case "priority":
                command = new PriorityCommand();
                break;
            case "bye":
                command = new ExitCommand();
                break;
            default:
                throw new UnknownCommandException();
            }
        }
        return command;
    }

    /**
     * Updates the previous command.
     * @param prevCommand Previous command.
     */
    public static void setPrevCommand(Command prevCommand) {
        Parser.prevCommand = prevCommand;
    }

    /**
     * Returns the correct TaskTypeCommand to handle user input.
     * @param input Input by the user.
     * @return An appropriate TaskTypeCommand.
     * @throws DukeException For when subsequent executions encounter an exception.
     */
    public static Command parseTaskType(String input) throws
            DukeException {
        switch (input.toLowerCase()) {
        case "todo":
            return new TaskTypeCommand(TaskType.TODO);
        case "deadline":
            return new TaskTypeCommand(TaskType.DEADLINE);
        case "event":
            return new TaskTypeCommand(TaskType.EVENT);
        default:
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Returns the correct TaskCommand to handle user input.
     * @param input Input by the user.
     * @param command TaskTypeCommand that stores the task type.
     * @return An appropriate TaskCommand.
     * @throws DukeException For when subsequent executions encounter an exception.
     */
    public static Command parseTask(String input, Command command) throws
            DukeException {
        assert command.getCommandType().equals(CommandType.TASKTYPE)
                : "Command type should be TASKTYPE";
        TaskTypeCommand c = (TaskTypeCommand) command;
        switch (c.getTaskType()) {
        case TODO:
            return new TaskCommand(TaskType.TODO, input);
        case DEADLINE:
            return new TaskCommand(TaskType.DEADLINE, input);
        case EVENT:
            return new TaskCommand(TaskType.EVENT, input);
        default:
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Returns the correct DeleteTaskCommand to handle user input.
     * @param input Input by the user.
     * @return An appropriate DeleteTaskCommand.
     * @throws WrongFormatException For when the user does not enter an integer task number.
     */
    public static Command parseDelete(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new DeleteTaskCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    /**
     * Returns the correct DoneTaskCommand to handle user input.
     * @param input Input by the user.
     * @return An appropriate DoneTaskCommand.
     * @throws WrongFormatException For when the user does not enter an integer task number.
     */
    public static Command parseDone(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new DoneTaskCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    /**
     * Returns the correct FindTaskCommand to handle user input.
     * @param input Input by the user.
     * @return An appropriate FindTaskCommand.
     */
    public static Command parseFind(String input) {
        return new FindTaskCommand(input);
    }

    /**
     * Returns the correct PriorityLevelCommand to handle user input.
     * @param input Input by the user.
     * @return An appropriate PriorityLevelCommand.
     * @throws WrongFormatException For when the user does not enter an integer task number.
     */
    public static Command parsePriority(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new PriorityLevelCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    /**
     * Returns the correct PrioritySetCommand to handle user input.
     * @param input Input by the user.
     * @param command PriorityLevelCommand storing the task to be updated.
     * @return An appropriate PrioritySetCommand.
     * @throws InvalidPriorityLevelException For when the user does not enter an appropriate priority level.
     */
    public static Command parsePriorityLevel(String input, Command command) throws
            InvalidPriorityLevelException {
        assert command.getCommandType().equals(CommandType.PRIORITYLEVEL)
                : "Command type should be PRIORITYLEVEL";
        PriorityLevelCommand c = (PriorityLevelCommand) command;
        switch (input.toLowerCase()) {
        case "high":
            return new PrioritySetCommand(c.getTaskNum(), PriorityLevel.HIGH);
        case "medium":
            return new PrioritySetCommand(c.getTaskNum(), PriorityLevel.MEDIUM);
        case "low":
            return new PrioritySetCommand(c.getTaskNum(), PriorityLevel.LOW);
        default:
            throw new InvalidPriorityLevelException();
        }
    }
}
