package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    static Command parse(String fullCommand) throws DukeException {
        String[] commandInputs = fullCommand.split(" ", 2);

        if (commandInputs.length == 0) {
            throw new DukeException("Something went wrong when parsing your inputs!");
        }

        // TODO: add test for case insensitivity
        String command = commandInputs[0].toLowerCase();

        String commandDetails;
        switch (command) {
            case "list":
                return new ListCommand();

            case "done":
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to mark a task as done, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    return new DoneCommand(taskId);
                } catch (NumberFormatException e) {
                    throw new DukeException(
                            "Please key in only the integer representing the task you want to mark as complete!");
                }

            case "delete":
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to delete a task, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    return new DeleteCommand(taskId);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please key in only the integer representing the task you want to delete!");
                }

            case "todo":
            case "deadline":
            case "event":
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to create new task without providing details!");
                }

                commandDetails = commandInputs[1];

                if (command.equals("todo")) {
                    return new AddCommand(new Todo(commandDetails));
                } else if (command.equals("deadline")) {
                    String[] deadlineDetails = commandDetails.split("/by", 2);

                    if (deadlineDetails.length < 2) {
                        throw new DukeException("Attempted to create task with deadline without specifying deadline!");
                    }

                    String description = deadlineDetails[0].trim();
                    String by = deadlineDetails[1].trim();

                    return new AddCommand(new Deadline(description, by));
                } else {
                    // Last case would be creating an event
                    String[] eventDetails = commandDetails.split("/at", 2);

                    if (eventDetails.length < 2) {
                        throw new DukeException("Attempted to create event without specifying time!");
                    }

                    String description = eventDetails[0].trim();
                    String at = eventDetails[1].trim();

                    return new AddCommand(new Event(description, at));
                }

            case "bye":
                return new ByeCommand();

            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }

}
