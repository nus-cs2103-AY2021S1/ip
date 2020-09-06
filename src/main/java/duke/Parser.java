package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents Parser Object which parses user reply and execute appropriate response.
 */
public class Parser {
    // check if user input contains a single word
    private static boolean checkIfSingleWordReply(String[] commandAndArguments) {
        return commandAndArguments.length == 1;
    }
    
    /**
     * Parses user input and returns appropriate Command Object for execution in Duke class.
     *
     * @param reply user message input into Intrubot.
     * @return Command to be executed by Duke class.
     * @throws DukeException if invalid command or arguments specified.
     */
    static Command parse(String reply) throws DukeException {
        String[] commandAndArguments = reply.split(" ");
        String command = commandAndArguments[0];
        boolean isSingleWordCommand = checkIfSingleWordReply(commandAndArguments);
        if (isSingleWordCommand) {
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "clear":
                return new ClearCommand();
            default:
                throw new DukeException(
                        String.format("Invalid Command Exception: %s is not a valid single word command.", command));
            }
        } else {
            try {
                switch (command) {
                case "done":
                    int indexOfTaskToMarkDone = parsedDoneArguments(reply);
                    return new DoneCommand(indexOfTaskToMarkDone);
                case "delete":
                    int indexOfTaskToDelete = parsedDeleteArguments(reply);
                    return new DeleteCommand(indexOfTaskToDelete);
                case "find":
                    String searchQuery = parseFindArguments(reply);
                    return new FindCommand(searchQuery);
                case "todo":
                    Task newTodo = parseTodoArguments(reply);
                    return new ToDoCommand(newTodo);
                case "deadline":
                    Task newDeadline = parseDeadlineArguments(reply);
                    return new DeadlineCommand(newDeadline);
                case "event":
                    Task newEvent = parseEventArguments(reply);
                    return new EventCommand(newEvent);
                default:
                    throw new DukeException(
                            String.format("Invalid Command Exception: %s is not a valid command.", command));
                }
            } catch (DateTimeParseException e) {
                throw new DukeException(String.format(
                        "Time format has to be in the form: YYYY-MM-DD %s", e.getMessage()));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException(String.format("Invalid arguments specified for %s", command));
            }
        }
    }

    /**
     * parse done command arguments for item index to mark done.
     *
     * @param reply user reply inclusive or arguments.
     * @return int of item index to mark done.
     */
    private static int parsedDoneArguments(String reply) {
        return Integer.parseInt(reply.split(" ")[1]);
    }

    /**
     * parse delete command arguments for item index to delete.
     *
     * @param reply user reply inclusive or arguments.
     * @return int of item index to delete.
     */
    private static int parsedDeleteArguments(String reply) {
        return Integer.parseInt(reply.split(" ")[1]);
    }

    /**
     * parse find command arguments for user search query.
     *
     * @param reply user reply inclusive or arguments.
     * @return string of user search query.
     */
    private static String parseFindArguments(String reply) {
        final int ARG_POSITION = 5;
        return reply.substring(ARG_POSITION);
    }

    /**
    /**
     * Parse event command arguments to create Todo object.
     *
     * @param reply user reply inclusive or arguments.
     * @return New Todo object based on command arguments.
     */
    private static Task parseTodoArguments(String reply) {
        final int ARG_POSITION = 5;
        Task newTodo = new ToDo(reply.substring(ARG_POSITION));
        return newTodo;
    }

    /**
     * Parse deadline command arguments to create deadline object.
     * 
     * @param reply user reply inclusive or arguments.
     * @return New deadline object based on command arguments.
     */
    private static Task parseDeadlineArguments(String reply) {
        final int ARG_POSITION = 9;
        String[] taskAndTimeByArray = reply.split(" /by ");
        String deadlineDescription = taskAndTimeByArray[0].substring(ARG_POSITION);
        String deadlineTiming = taskAndTimeByArray[1];
        Task newDeadline = new Deadline(deadlineDescription, LocalDate.parse(deadlineTiming));
        return newDeadline;
    }

    /**
     * Parse event command arguments to create event object.
     * 
     * @param reply user reply inclusive or arguments.
     * @return New event object based on command arguments.
     */
    private static Task parseEventArguments(String reply) {
        final int ARG_POSITION = 6;
        String[] taskAndTimeAtArray = reply.split(" /at ");
        String eventDescription = taskAndTimeAtArray[0].substring(ARG_POSITION);
        String eventTiming = taskAndTimeAtArray[1];
        Task newEvent = new Event(eventDescription, LocalDate.parse(eventTiming));
        return newEvent;
    }
}
