package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.PriorityLevel;
import duke.task.TaskType;


public class Parser {
    protected static Command prevCommand = new ResetCommand();

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

    public static void setPrevCommand(Command prevCommand) {
        Parser.prevCommand = prevCommand;
    }

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

    public static Command parseDelete(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new DeleteTaskCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    public static Command parseDone(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new DoneTaskCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    public static Command parseFind(String input) {
        return new FindTaskCommand(input);
    }

    public static Command parsePriority(String input) throws WrongFormatException {
        try {
            int taskNum = Integer.parseInt(input);
            return new PriorityLevelCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

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
