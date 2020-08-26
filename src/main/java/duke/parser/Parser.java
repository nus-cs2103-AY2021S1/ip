package duke.parser;

import duke.command.*;
import duke.exceptions.*;

/**
 * Parses user's input.
 */
public class Parser {

    /**
     * Takes in user's input and return the corresponding command for the input
     *
     * @param userInput user's input.
     * @return UserCommand
     * @throws DukeException
     */
    public static UserCommand parse(String userInput) throws DukeException {
        while (!userInput.equals("bye") || userInput != null) {
            String firstArg = userInput.split(" ")[0];
            try {
                switch (userInput) {
                    case "bye":
                        return new ByeCommand(userInput);
                    case "list":
                        return new ListCommand(userInput);
                    default:
                        switch (firstArg) {
                            case "done":
                                return new DoneCommand(userInput);
                            case "delete":
                                return new DeleteCommand(userInput);
                            case "todo":
                                return new ToDoCommand(userInput);
                            case "deadline":
                                return new DeadlineCommand(userInput);
                            case "event":
                                return new EventCommand(userInput);
                            default:
                                return new InvalidCommand(userInput);
                        }
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }
}
