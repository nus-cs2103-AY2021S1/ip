import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

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
                return new listCommand(tasks);
            case "delete":
                try {
                    int deleteTaskNo = Integer.parseInt(description);
                    return new deleteCommand(tasks, deleteTaskNo);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please include a task number after 'delete' command.");
                }
            case "done":
                int doneTaskNo = Integer.parseInt(description);
                return new doneCommand(tasks, doneTaskNo);
            case "todo":
                if (description != null) {
                    Todo newTodo = new Todo(description);
                    return new addCommand(tasks, newTodo);
                } else {
                    throw new DukeException("The description of a todo cannot be empty.\n");
                }
            case "deadline":
                if (description != null) {
                    try {
                        LocalDate deadlineDate = LocalDate.parse(description.substring(description.indexOf("/by") + 4));
                        String deadlineDescription = description.substring(0, description.indexOf("/by") - 1);
                        Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                        return new addCommand(tasks, newDeadline);
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
                        return new addCommand(tasks, newEvent);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new DukeException("Please indicate event details formatted as: /at ______ .\n");
                    }
                } else {
                    throw new DukeException("The description of a event cannot be empty.\n");
                }
            case "bye":
                return new endCommand(tasks);

            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
