package duke;

import duke.command.*;
import duke.task.*;

import java.time.format.DateTimeParseException;

public class Parser { // deals with making sense of the user command
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.split(" ", 2); // limit is the result threshold; return 2 strings
            CommandTypes command = CommandTypes.valueOf(splitCommand[0].toUpperCase());
            switch (command) { // use switch case for easy scalability
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case DONE:
                    return new DoneCommand(Integer.parseInt(splitCommand[1])); // if cannot change to integer,
                case DELETE:                                                   // catch NumberFormatException
                    return new DeleteCommand(Integer.parseInt(splitCommand[1]));
                case TODO:
                    ToDo todo = new ToDo(splitCommand[1]);
                    return new AddCommand(todo);
                case DEADLINE:
                    String[] splitDeadline = splitCommand[1].split(" /by ", 2);
                    Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                    return new AddCommand(deadline);
                case EVENT:
                    String[] splitEvent = splitCommand[1].split(" /at ", 2);
                    Event event = new Event(splitEvent[0], splitEvent[1]);
                    return new AddCommand(event);
                default:
                    throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException("SORRY!!! Task number is not valid.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("SORRY!!! I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("SORRY!!! The description of a task cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("SORRY!!! Wrong date format encountered!");
        }
    }
}