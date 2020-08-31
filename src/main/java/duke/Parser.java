package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] splitCommands = fullCommand.split(" ", 2);
        splitCommands[0] = splitCommands[0].strip().toUpperCase();
        try {
            switch (splitCommands[0]) {
                case "TODO":
                    ToDos todo = new ToDos(splitCommands[1]);
                    return new AddCommand(todo);
                case "DEADLINE":
                    String[] splitDeadline = splitCommands[1].split("/by", 2);
                    Deadlines deadline = new Deadlines(splitDeadline[0].strip(), LocalDate.parse(splitDeadline[1].strip()));
                    return new AddCommand(deadline);
                case "EVENT":
                    String[] splitEvent = splitCommands[1].split("/at", 2);
                    Events event = new Events(splitEvent[0].strip(), LocalDate.parse(splitEvent[1].strip()));
                    return new AddCommand(event);
                case "DELETE":
                    return new DeleteCommand(Integer.parseInt(splitCommands[1].strip()));
                case "LIST":
                    return new ListCommand();
                case "DONE":
                    return new DoneCommand(Integer.parseInt(splitCommands[1].strip()));
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
                    throw new DukeException("Uh-oh! You did not enter the number of the task you wish to delete.");
                case "DONE":
                    throw new DukeException("Uh-oh! You did not enter the number of the task you wish to complete.");
                default:
                    throw new DukeException("Uh-oh! The description of your task cannot be empty.");
            }
        }
    }
}
