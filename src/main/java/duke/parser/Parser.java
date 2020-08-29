package duke.parser;


import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

public class Parser {
    /**
     * Parses the input and turn it into a Command.
     *
     * @param input input to be parsed
     * @return the Command that the input belongs to
     * @throws DukeException DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        Command command;

        switch (inputArr[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(Integer.parseInt(inputArr[1]));
            break;
        case "find":
            try {
                command = new FindCommand(input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "delete":
            try {
                command = new DeleteCommand(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please include item number!");
            }
            break;
        case "todo":
            try {
                command = new AddCommand("todo", input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "deadline":
            try {
                command = new AddCommand("deadline", input.substring(9));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "event":
            try {
                command = new AddCommand("event", input.substring(6));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        default:
            command = new UnknownCommand();
            break;
        }
        return command;
    }
}


