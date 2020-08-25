package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;

public class Parser {
    static Command parse (String fullCommand) throws InvalidInputException, InvalidCommandException {
        String[] commandArr = fullCommand.trim().split(" ", 2);
        switch(commandArr[0]) {
            case "bye":
                return new ExitCommand();

            case "todo":
            case "deadline":
            case "event":
                return prepareAdd(commandArr[0], commandArr[1]);

            case "delete":
                return prepareDelete(commandArr[1]);

            case "done":
                return prepareDone(commandArr[1]);

            case "list":
                return new ListCommand();

            default:
                throw new InvalidCommandException("Invalid Command: " + commandArr[0]);
        }
    }


    static Command prepareAdd(String command, String taskDetails) throws InvalidInputException, InvalidCommandException {
        switch (command) {
            case "todo": {
                String task = taskDetails.trim();
                if (task.isEmpty()) {
                    throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(task, null, TaskType.TODO);
            }
            case "deadline": {
                String[] task = taskDetails.trim().split(" /by ");
                if (task[0].isEmpty()) {
                    throw new InvalidInputException("☹ OOPS!!! The description of a deadline task cannot be empty.");
                }
                if (task.length < 2) {
                    throw new InvalidInputException("☹ OOPS!!! The deadline of a deadline task cannot be empty.");
                }
                return new AddCommand(task[0], task[1], TaskType.DEADLINE);
            }
            case "event": {
                String[] task = taskDetails.trim().split(" /at ");
                if (task[0].isEmpty()) {
                    throw new InvalidInputException("☹ OOPS!!! The description of an event task cannot be empty.");
                }
                if (task.length < 2) {
                    throw new InvalidInputException("☹ OOPS!!! The timing of an event task cannot be empty.");
                }
                return new AddCommand(task[0], task[1], TaskType.EVENT);
            }
            default:
                throw new InvalidCommandException("Invalid Command: " + command);
        }
    }

    static Command prepareDelete(String index) {
        int taskIndex = Integer.valueOf(index);
        return new DeleteCommand(taskIndex);
    }

    static Command prepareDone(String index) {
        int taskIndex = Integer.valueOf(index);
        return new DoneCommand(taskIndex);
    }
}
