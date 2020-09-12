package duke;

import java.util.Scanner;

import command.*;

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
        String parameters = input.replace(command,"");
        switch (command) {
        case "list":
            return new GetTaskListCommand();
        case "date":
            return new FindTaskByDateCommand(parameters.strip());
        case "done":
            return new MarkTaskDoneCommand(parameters.strip());
        case "delete":
            return new DelTaskCommand(parameters.strip());
        case "event":
            return new CreateEventTaskCommand(parameters.split("/at"));
        case "deadline" :
            return new CreateDeadLineTaskCommand(parameters.split("/by"));
        case "todo":
            return new CreateTodoTaskCommand(parameters);
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindTaskByKeywordCommand(sc.nextLine().strip());
        default:
            throw new IllegalArgumentException();
        }
    }

}
