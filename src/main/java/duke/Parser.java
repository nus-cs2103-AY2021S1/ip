package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.InvalidTaskIdException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskDetailsException;
import duke.exception.MissingTaskIdException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    private final static String failedToMarkTaskAsComplete = "Failed to mark task as complete!";
    private final static String failedToDeleteTask = "Failed to delete task!";
    private final static String failedToCreateTask = "Failed to create task!";
    private final static String failedToCreateDeadline = "Failed to create Deadline task!";
    private final static String failedToCreateEvent = "Failed to create Event task!";

    static Command parse(String fullCommand) throws DukeException {
        String[] commandInputs = fullCommand.trim().split(" ", 2);

        if (commandInputs.length == 0) {
            throw new DukeException("Something went wrong when parsing your inputs!");
        }

        String command = commandInputs[0].trim().toLowerCase();

        String commandDetails;

        switch (command) {
            case "list":
                return new ListCommand();

            case "find":
                if (commandInputs.length < 2) {
                    throw new MissingKeywordException("I'm not sure what tasks to search for...");
                }

                commandDetails = commandInputs[1].trim();

                return new FindCommand(commandDetails);

            case "done":
                if (commandInputs.length < 2) {
                    throw new MissingTaskIdException(failedToMarkTaskAsComplete);
                }

                commandDetails = commandInputs[1].trim();

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    return new DoneCommand(taskId);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskIdException(failedToMarkTaskAsComplete);
                }

            case "delete":
                if (commandInputs.length < 2) {
                    throw new MissingTaskIdException(failedToDeleteTask);
                }

                commandDetails = commandInputs[1].trim();

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    return new DeleteCommand(taskId);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskIdException(failedToDeleteTask);
                }

            case "todo":
            case "deadline":
            case "event":
                if (commandInputs.length < 2) {
                    throw new MissingTaskDetailsException(failedToCreateTask);
                }

                commandDetails = commandInputs[1].trim();

                if (command.equals("todo")) {
                    return new AddCommand(new Todo(commandDetails));
                } else if (command.equals("deadline")) {
                    String[] deadlineDetails = commandDetails.split("/by", 2);

                    if (deadlineDetails.length < 2) {
                        throw new MissingTaskDetailsException(failedToCreateDeadline,
                                "No deadline was specified!");
                    }

                    String description = deadlineDetails[0].trim();
                    String by = deadlineDetails[1].trim();

                    return new AddCommand(new Deadline(description, by));
                } else {
                    // Last case would be creating an event
                    String[] eventDetails = commandDetails.split("/at", 2);

                    if (eventDetails.length < 2) {
                        throw new MissingTaskDetailsException(failedToCreateEvent,
                                "No date was specified!");
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
