package main.java;

import main.java.command.*;
import main.java.exceptions.DukeException;

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
            } else {
                return addCommand(userInput);
            }
    }

    public static Command addCommand(String command) throws DukeException {
        String[] cmdLine = command.split(" ");
        String taskType = cmdLine[0];

        switch (taskType) {
            case "todo":
                return new TodoCommand(command);

            case "deadline":
                return new DeadlineCommand(command);

            case "event":
                return new EventCommand(command);

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
