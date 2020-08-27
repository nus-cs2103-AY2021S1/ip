package duke.parser;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;

/**
 * Parser class.
 * Define rule to determine which command is use.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Parser {
    /**
     * return command type that will be used.
     *
     * @param fullCommand full command that the user input.
     * @return command type.
     * @throws DukeException input command does not fit any of the application functionalities.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        switch (command) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(fullCommand);
            case "todo":
                return new ToDoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means");
        }
    }
}
