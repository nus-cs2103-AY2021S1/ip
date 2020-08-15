package parser;

import operation.Operation;
import operation.AddOperation;
import operation.DoneOperation;
import operation.ListOperation;
import operation.ExitOperation;
import task.TaskStorage;

public class CommandParser {
    private String[] parseCommandString(String commandString) {
        String[] commands = commandString.split(" ");
        return commands;
    }

    public Operation parse(String commandString, TaskStorage taskStorage) {
        String[] commands = parseCommandString(commandString);
        switch(commands[0]) {
            case "bye":
                return new ExitOperation(commands);
            case "list":
                return new ListOperation(commands, taskStorage);
            case "done":
                return new DoneOperation(commands, taskStorage);
            default :
                return new AddOperation(commands, taskStorage);
        }
    }
}