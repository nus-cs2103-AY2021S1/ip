package main.java.duke;

import main.java.duke.command.*;
import main.java.duke.exceptions.DukeException;

public class Parser {

    public static Command parseCommand(String userInput) throws DukeException {
            if (userInput.startsWith("done")) {
                return new DoneCommand(userInput);
            } else if (userInput.startsWith("delete")) {
                return new DeleteCommand(userInput);
            } else if (userInput.equals("bye")) {
                return new ExitCommand(userInput);
            } else if (userInput.equals("list")) {
                return new ListCommand(userInput);
            } else if (userInput.startsWith("find")) {
                return new FindCommand(userInput);
            } else {
                return addCommand(userInput);
            }
    }

    public static Command addCommand(String command) throws DukeException {
        if (command.startsWith("todo")) {
            return new TodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return new DeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return new EventCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
