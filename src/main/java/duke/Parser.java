package duke;

import duke.command.*;
import duke.task.*;

import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.trim().split(" ", 2);
            CommandInstruction instruction = CommandInstruction.valueOf(splitCommand[0].toUpperCase());
            switch (instruction) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                return new DoneCommand(Integer.parseInt(splitCommand[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(splitCommand[1]));
            case TODO:
                Todo todo = new Todo(splitCommand[1]);
                return new AddCommand(todo);
            case DEADLINE:
                String[] splitDeadline = splitCommand[1].split(" /by ", 2);
                Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                return new AddCommand(deadline);
            case EVENT:
                String[] splitEvent = splitCommand[1].split(" /at ", 2);
                Event event = new Event(splitEvent[0], splitEvent[1]);
                return new AddCommand(event);
            case VIEW:
                return new ViewCommand(splitCommand[1]);
            case FIND:
                return new FindCommand(splitCommand[1]);
            default:
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The task number is not valid.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description or date cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date is not valid.");
        }
    }
}
