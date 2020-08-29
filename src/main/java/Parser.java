import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EndCommand;
import command.FindCommand;
import command.ListCommand;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class Parser {
    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the user input and returns a Command to be executed.
     *
     * @param input String input from user.
     * @param tasks TaskList being used to handle tasks.
     * @return Command to be executed.
     * @throws DukeException if user input is invalid or improperly formatted.
     */
    public Command parse(String input, TaskList tasks) throws DukeException {
        String command;
        String description = null;

        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            description = input.substring(input.indexOf(" ") + 1);
        } else {
            command = input;
        }

        switch (command) {
        case "list":
            return new ListCommand(tasks);
        case "delete":
            try {
                int deleteTaskNo = Integer.parseInt(description);
                return new DeleteCommand(tasks, deleteTaskNo);
            } catch (NumberFormatException e) {
                throw new DukeException("Please include a task number after 'delete' command.");
            }
        case "done":
            int doneTaskNo = Integer.parseInt(description);
            return new DoneCommand(tasks, doneTaskNo);
        case "todo":
            if (description != null) {
                Todo newTodo = new Todo(description);
                return new AddCommand(tasks, newTodo);
            } else {
                throw new DukeException("The description of a todo cannot be empty.\n");
            }
        case "deadline":
            if (description != null) {
                try {
                    LocalDate deadlineDate = LocalDate.parse(description.substring(description.indexOf("/by") + 4));
                    String deadlineDescription = description.substring(0, description.indexOf("/by") - 1);
                    Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                    return new AddCommand(tasks, newDeadline);
                } catch (DateTimeParseException ex) {
                    throw new DukeException("Please indicate deadline date formatted as: /by YYYY-MM-DD.");
                }
            } else {
                throw new DukeException("The description of a deadline cannot be empty.\n");
            }
        case "event":
            if (description != null) {
                try {
                    String eventAt = description.substring(description.indexOf("/at") + 4);
                    String eventDescription = description.substring(0, description.indexOf("/at") - 1);
                    Event newEvent = new Event(eventDescription, eventAt);
                    return new AddCommand(tasks, newEvent);
                } catch (StringIndexOutOfBoundsException ex) {
                    throw new DukeException("Please indicate event details formatted as: /at ______ .\n");
                }
            } else {
                throw new DukeException("The description of a event cannot be empty.\n");
            }
        case "bye":
            return new EndCommand(tasks);
        case "find":
            return new FindCommand(tasks, description);

        default:
            duke.ui.badInput();
        }
        return null;
    }
}
