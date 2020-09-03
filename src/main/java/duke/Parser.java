package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
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
        fullCommand = fullCommand.strip().replaceAll("\\s+", " ");
        String[] splitCommands = fullCommand.split(" ", 2);
        splitCommands[0] = splitCommands[0].toUpperCase();
        try {
            switch (splitCommands[0]) {
            case "TODO":
                ToDo todo = new ToDo(splitCommands[1]);
                return new AddCommand(todo);
            case "DEADLINE":
                String[] splitDeadline = splitCommands[1].split(" /by ", 2);
                Deadline deadline = new Deadline(splitDeadline[0], LocalDate.parse(splitDeadline[1]));
                return new AddCommand(deadline);
            case "EVENT":
                String[] splitEvent = splitCommands[1].split(" /at ", 2);
                Event event = new Event(splitEvent[0], LocalDate.parse(splitEvent[1]));
                return new AddCommand(event);
            case "DELETE":
                return new DeleteCommand(Integer.parseInt(splitCommands[1]));
            case "FIND":
                return new FindCommand(splitCommands[1]);
            case "LIST":
                return new ListCommand();
            case "DONE":
                return new DoneCommand(Integer.parseInt(splitCommands[1]));
            case "BYE":
                return new ExitCommand();
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
            switch (splitCommands[0]) {
            case "DELETE":
                throw new DukeException(
                        "Uh-oh! You did not enter the number of the task you wish to delete.");
            case "DONE":
                throw new DukeException(
                        "Uh-oh! You did not enter the number of the task you wish to complete.");
            default:
                if (splitCommands.length > 1) {
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
