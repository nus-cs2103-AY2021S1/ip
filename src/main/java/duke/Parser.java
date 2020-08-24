package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses user input and carry out operations on user's tasks
     */
    static Command parse(String reply) throws DukeException {
        String[] replyArray = reply.split(" ");
        String command = replyArray[0];
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else {
            try {
                switch (command) {
                case "done":
                    return new DoneCommand(reply);
                case "delete":
                    return new DeleteCommand(reply);
                case "todo":
                    Task newTodo = new ToDo(reply.substring(5));
                    return new ToDoCommand(newTodo);
                case "deadline":
                    String[] taskAndTimeByArray = reply.split(" /by ");
                    String deadlineDescription = taskAndTimeByArray[0].substring(9);
                    String by = taskAndTimeByArray[1];
                    Task newDeadline = new Deadline(deadlineDescription, LocalDate.parse(by));
                    return new DeadlineCommand(newDeadline);
                case "event":
                    String[] taskAndTimeAtArray = reply.split(" /at ");
                    String eventDescription = taskAndTimeAtArray[0].substring(6);
                    String at = taskAndTimeAtArray[1];
                    Task newEvent = new Event(eventDescription, LocalDate.parse(at));
                    return new EventCommand(newEvent);
                default:
                    throw new DukeException(
                            "Invalid Command Exception, start every cmd with todo, deadline or event");
                }
            } catch (DateTimeParseException e) {
                throw new DukeException(String.format(
                        "Time format has to be in the form: YYYY-MM-DD %s", e.getMessage()));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException(String.format("Invalid arguments specified for %s", command));
            }
        }
    }   
}
