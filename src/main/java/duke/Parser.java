package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;

public class Parser {
    private TaskList taskList;
    private boolean terminate = false;
    private String filePath;
    private String lineSeparator = System.getProperty("line.separator");
    private String taskDetailsSeparator = " | ";
    Ui ui;

    public Parser() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }


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
                System.out.println(error.getMessage());
            }
        }
        case DELETE: {
            int indexOfTaskToBeDeleted = Integer.parseInt(splitCommand[1]);
            return parseDelete(indexOfTaskToBeDeleted);
        }
        case FIND : {
            return parseFind(splitCommand[1]);
        }
        default: {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        }
    }

    public static ListCommand parseList() {
        return new ListCommand();
    }

    public static ByeCommand parseBye() {
        return new ByeCommand();
    }

    public static DoneCommand parseDone(int indexOfTaskToBeDone) {
        return new DoneCommand(indexOfTaskToBeDone);
    }

    public static DeadlineCommand parseDeadline(String details) {
        String[] deadlineTaskDetails = details.split(" /by ");
        return new DeadlineCommand(deadlineTaskDetails);
    }

    public static EventCommand parseEvent(String details) {
        String[] eventTaskDetails = details.split(" /at ");
        return new EventCommand(eventTaskDetails);
    }

    public static TodoCommand parseTodo(String details) {
        return new TodoCommand(details);
    }

    public static DeleteCommand parseDelete(int indexOfTaskToBeDeleted) {
        return new DeleteCommand(indexOfTaskToBeDeleted);
    }

    public static FindCommand parseFind(String keyword) {
        return new FindCommand(keyword);
    }
}