package duke.parser;

import duke.command.*;
import duke.exceptions.*;

public class Parser {

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
