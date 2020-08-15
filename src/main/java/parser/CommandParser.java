package parser;

import java.lang.StringBuilder;

import exception.DukeException;
import operation.Operation;
import operation.AddDeadlineOperation;
import operation.AddEventOperation;
import operation.AddTodoOperation;
import operation.DeleteOperation;
import operation.DoneOperation;
import operation.ExitOperation;
import operation.ListOperation;
import task.TaskStorage;

public class CommandParser {
    private static int getForwardSlashIndex(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }

    private static String concatenate(String[] arr, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < end; i++) {
            builder.append(arr[i]);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private String[] parseCommandString(String commandString) {
        String[] commands = commandString.split(" ");
        String mainCommand = commands[0];
        String description;
        String datetime;
        int splitIndex = getForwardSlashIndex(commands);

        if (commands.length == 1) {
            return new String[]{mainCommand};
        } else if (splitIndex == 0 || splitIndex == commands.length - 1) {
            description = concatenate(commands, 1, commands.length);
            return new String[] {mainCommand, description};
        } else {
            description = concatenate(commands, 1, splitIndex);
            datetime = concatenate(commands, splitIndex + 1, commands.length);
            return new String[] {mainCommand, description, datetime};
        }
    }

    public Operation parse(String commandString, TaskStorage taskStorage) throws DukeException {
        String[] commands = parseCommandString(commandString);
        switch(commands[0]) {
            case "bye":
                return new ExitOperation(commands);
            case "list":
                return new ListOperation(commands, taskStorage);
            case "done":
                return new DoneOperation(commands, taskStorage);
            case "todo" :
                return new AddTodoOperation(commands, taskStorage);
            case "deadline" :
                return new AddDeadlineOperation(commands, taskStorage);
            case "event" :
                return new AddEventOperation(commands, taskStorage);
            case "delete":
                return new DeleteOperation(commands, taskStorage);
            default:
                throw new DukeException("This command is not recognised unfortunately.");
        }
    }
}