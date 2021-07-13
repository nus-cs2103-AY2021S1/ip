package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents the part of Duke that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the command given by the user so that Duke can execute it.
     *
     * @param fullCommand the command given by the user.
     * @return the command interpreted by Duke from the command given by the user.
     * @throws DukeException if the command given by the user is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        // Replace excess whitespaces with just one
        fullCommand = fullCommand.strip().replaceAll("\\s+", " ");
        // Split into command and description
        String[] splitCommand = fullCommand.split(" ", 2);
        // Convert command to uppercase to standardize
        String command = splitCommand[0].toUpperCase();

        try {
            switch (command) {
            case "TODO":
                String description = splitCommand[1];
                ToDo todo = new ToDo(description);
                return new AddCommand(todo);
            case "DEADLINE":
                // Split into description and due date
                String[] splitDeadline = splitCommand[1].split(" /by ", 2);

                description = splitDeadline[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dueDate = LocalDate.parse(splitDeadline[1], formatter);
                Deadline deadline = new Deadline(description, dueDate);
                return new AddCommand(deadline);
            case "EVENT":
                // Split into description and event date
                String[] splitEvent = splitCommand[1].split(" /on ", 2);

                description = splitEvent[0];
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate eventDate = LocalDate.parse(splitEvent[1], formatter);
                Event event = new Event(description, eventDate);
                return new AddCommand(event);
            case "DELETE":
                int taskNum = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskNum);
            case "FIND":
                String keyword = splitCommand[1];
                return new FindCommand(keyword);
            case "LIST":
                return new ListCommand();
            case "DONE":
                taskNum = Integer.parseInt(splitCommand[1]);
                return new DoneCommand(taskNum);
            case "BYE":
                return new ExitCommand();
            case "CLEAR":
                return new ClearCommand();
            default:
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Uh-oh! Looks like you have entered an invalid task number.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Uh-oh! I have no idea what that means.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Uh-oh! Looks like you have got the format for the date wrong.");
        } catch (IndexOutOfBoundsException e) {
            switch (command) {
            case "DELETE":
                throw new DukeException(
                        "Uh-oh! You did not enter the number of the task you wish to delete.");
            case "DONE":
                throw new DukeException(
                        "Uh-oh! You did not enter the number of the task you wish to complete.");
            default:
                if (splitCommand.length > 1) {
                    throw new DukeException(
                            "Uh-oh! Looks like you have got the format for the description wrong.");
                } else {
                    throw new DukeException(
                            "Uh-oh! The description of your task cannot be empty.");
                }
            }
        }
    }
}
