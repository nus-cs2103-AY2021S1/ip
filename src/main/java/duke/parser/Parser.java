package duke.parser;

import java.time.LocalDate;

import duke.DukeException;
import duke.command.*;
import duke.task.*;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String arr[] = fullCommand.split(" ", 2);
        String command = arr[0];
        String remainingText = arr.length == 1 ? null : arr[1].trim();

        switch (command) {
            case "list": {
                if (remainingText != null) throw new DukeException("Did you mean to say \'list\'?");
                return new ListCommand();
            }
            case "find": {
                if (remainingText == null) throw new DukeException("The keyword for finding a task cannot be empty.");
                return new FindCommand(remainingText);
            }
            case "todo": {
                if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                Task task = new TodoTask(remainingText);
                return new AddCommand(task);
            }
            case "deadline": {
                if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                String taskItems[] = remainingText.split(" /by ");
                String description = taskItems[0].trim();
                if (taskItems.length == 1) throw new DukeException("The date for a deadline cannot be empty.");
                LocalDate by = LocalDate.parse(taskItems[1].trim());
                Task task = new DeadlineTask(description, by);
                return new AddCommand(task);
            }
            case "event": {
                if (remainingText == null) throw new DukeException("The description for a task cannot be empty.");
                String taskItems[] = remainingText.split(" /at ");
                String description = taskItems[0].trim();
                if (taskItems.length == 1) throw new DukeException("The date for an event cannot be empty.");
                LocalDate at = LocalDate.parse(taskItems[1].trim());
                Task task = new EventTask(description, at);
                return new AddCommand(task);
            }
            case "done": {
                if (remainingText == null) throw new DukeException("Please specify an item number.");
                int index = Integer.parseInt(remainingText) - 1;
                return new DoneCommand(index);
            }
            case "delete": {
                if (remainingText == null) throw new DukeException("Please specify an item number.");
                int index = Integer.parseInt(remainingText) - 1;
                return new DeleteCommand(index);
            }
            case "bye": {
                if (remainingText != null) throw new DukeException("Did you mean to say \'bye\'?");
                return new ByeCommand();
            }
            default: {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
