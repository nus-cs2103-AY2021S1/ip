package duke.parser;

import duke.command.ArchiveCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EnumCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

/**
 * A class to analyse the user's input and determine what command it is.
 */
public class Parser {
    public Parser() {
    }
    /**
     * Parses the input from the user and turn it into a command.
     * @param nextCommand the string of instruction input by the user
     * @return Command that represents what the user wants Duke to do.
     * @throws DukeException if the user's input is not a valid Command type for Duke.
     */
    public static Command parse(String nextCommand) throws DukeException {
        String[] nextCommandArr = nextCommand.split(" ", 2);
        assert nextCommandArr.length <= 2 : "The length of next command array cannot be more than 2";
        Command next;
        EnumCommand enumCommand;
        String command = nextCommandArr[0];
        if (command.equals("bye")) {
            enumCommand = EnumCommand.BYE;
        } else if (command.equals("list")) {
            enumCommand = EnumCommand.LIST;
        } else if (command.equals("done")) {
            enumCommand = EnumCommand.DONE;
        } else if (command.equals("todo")) {
            enumCommand = EnumCommand.TODO;
        } else if (command.equals("deadline")) {
            enumCommand = EnumCommand.DEADLINE;
        } else if (command.equals("event")) {
            enumCommand = EnumCommand.EVENT;
        } else if (command.equals("delete")) {
            enumCommand = EnumCommand.DELETE;
        } else if (command.equals("find")) {
            enumCommand = EnumCommand.FIND;
        } else if (command.equals("archive")) {
            enumCommand = EnumCommand.ARCHIVE;
        } else {
            throw new DukeException("Sorry, I don't know what that means~");
        }
        switch (enumCommand) {
        case BYE:
            next = new ByeCommand();
            break;
        case LIST:
            next = new ListCommand();
            break;
        case DONE:
            next = new DoneCommand(nextCommandArr);
            break;
        case TODO:
            next = new TodoCommand(nextCommandArr);
            break;
        case DEADLINE:
            next = new DeadlineCommand(nextCommandArr);
            break;
        case EVENT:
            next = new EventCommand(nextCommandArr);
            break;
        case DELETE:
            next = new DeleteCommand(nextCommandArr);
            break;
        case FIND:
            next = new FindCommand(nextCommandArr);
            break;
        case ARCHIVE:
            next = new ArchiveCommand(nextCommandArr);
            break;
        default:
            throw new DukeException("Sorry, I don't know what that means~");
        }
        assert next != null : "Command cannot be empty";
        return next;
    }
}
