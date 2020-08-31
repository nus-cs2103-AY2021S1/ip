package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandTypes;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.split(" ", 2);
            CommandTypes command = CommandTypes.valueOf(splitCommand[0].toUpperCase());
            switch (command) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case DONE:
                    return new DoneCommand(Integer.parseInt(splitCommand[1]));
                case DELETE:
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