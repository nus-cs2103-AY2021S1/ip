package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;



public class Parser {

    /**
     * Converts user input to its associated type of Command.
     * @param input user input
     * @return specific type of Command
     * @throws DukeException if user input does not follow convention
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.equals("sort")) {
            return new SortCommand();
        }

        String[] process = input.split(" ", 2);
        String first = process[0].trim();

        try {
            String second = process[1].trim();

            switch (first) {
            case "done": {
                return new DoneCommand(second);
            }

            case "delete": {
                return new DeleteCommand(second);
            }

            case "check": {
                return new CheckCommand(second);
            }

            case "find": {
                return new FindCommand(second);
            }

            default: {
                return new AddCommand(first, second);
            }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Only one word detected. Remember to add whitespace"
                    + " between information or enter a valid one word command!");
        }
    }
}
