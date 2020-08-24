package main.java.duke;

import main.java.duke.command.ByeCommand;
import main.java.duke.command.Command;
import main.java.duke.command.DeadlineCommand;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.EventCommand;
import main.java.duke.command.ListCommand;
import main.java.duke.command.ToDoCommand;

public class Parser {
    public static Command parse(String command) throws DukeException {
        String[] inputArray = command.split("\\s+");
        switch (inputArray[0]) {
        case "bye":
            return new ByeCommand();
        case "":
            return null;
        case "todo":
            return new ToDoCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "event":
            return new EventCommand(command);
        case "done":
            return new DoneCommand(inputArray);
        case "delete":
            return new DeleteCommand(inputArray);
        case "list":
            return new ListCommand(inputArray);
        default:
            throw new DukeException("I'm not sure what you're talking about.");
        }
    }
}
