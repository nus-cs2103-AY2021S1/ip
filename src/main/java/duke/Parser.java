package duke;

import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.DateCommand;
import command.DeadLineCommand;
import command.DelCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
/**
 * The Parser class to handle the parsing of user inputs to the appropriate commands.
 *
 * @author  Ryan Lim
 */
public class Parser {

    /**
     *  returns the command (and its parameters) based on the user input that has been parsed
     *
     * @param input user input
     * @return Command based on user input
     * @throws IllegalArgumentException if user input does not match the appropriate commands
     */
    public Command parse(String input) throws IllegalArgumentException {
        assert input.length() > 0 : "no input given";
        Scanner sc = new Scanner(input);
        String command = sc.next().toLowerCase();
        switch (command) {
        case "list":
            return new ListCommand();
        case "date":
            return new DateCommand(sc.nextLine().strip());
        case "done":
            return new DoneCommand(sc.nextLine().strip());
        case "delete":
            return new DelCommand(sc.nextLine().strip());
        case "event":
            return new EventCommand(sc.nextLine().split("/at"));
        case "deadline" :
            return new DeadLineCommand(sc.nextLine().split("/by"));
        case "todo":
            return new TodoCommand(sc.nextLine());
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(sc.nextLine().strip());
        default:
            throw new IllegalArgumentException();
        }
    }

}
