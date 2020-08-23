package duke.utils;

import duke.Duke;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;


/**
 * Handles all the commands from the user.
 */
public class Parser {

    /**
     * Converts the users input from string to Command type.
     *
     * @param input string the user entered.
     * @return Command which the user wanted.
     * @throws DukeException if the command format is wrong.
     */
    public static Command parse(String input) throws DukeException {
        switch (input.split(" ")[0].toLowerCase()) {
        case "":
            throw new DukeException("Please type a duke.command");
        case "bye":
            return new ExitCommand();
        case "clear":
            return new ClearCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(input.substring(5)) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(input.substring(7)) - 1);
        case "deadline":
            try {
                int by = input.indexOf(" /by");
                return new DeadlineCommand(input.substring(9, by), input.substring(by + 5));
            } catch (Exception e) {
                throw new DukeException("Deadline format isn't correct");
            }
        case "event":
            try {
                int at = input.indexOf(" /at");
                return new EventCommand(input.substring(6, at), input.substring(at + 5));
            } catch (Exception e) {
                throw new DukeException("Event format isn't correct");
            }
        case "todo":
            return new TodoCommand(input.substring(5));
        case "find":
            try {
                return new FindCommand(input.substring(5));
            } catch (Exception e) {
                throw new DukeException("Find format isn't correct");
            }
        default:
            throw new DukeException("I don't know what that means :( ");
        }
    }
}