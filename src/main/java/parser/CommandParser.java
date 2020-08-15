package parser;

import operation.Operation;
import operation.AddOperation;
import operation.ExitOperation;
import operation.ListOperation;
import task.TaskStorage;

public class CommandParser {
    public Operation parse(String command, TaskStorage taskStorage) {
        switch(command) {
            case "bye":
                return new ExitOperation(command);
            case "list":
                return new ListOperation(command, taskStorage);
            default :
                return new AddOperation(command, taskStorage);
        }
    }
}