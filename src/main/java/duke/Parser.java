package duke;

import duke.command.*;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

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