package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

public class Parser {

    public static Command add(String command) throws DukeEmptyDescriptionException,
            DukeEmptyByException, DukeEmptyAtException, DukeUnknownInputException {
        Task toBeAdded;
        String des;
        String[] tokens;
        if (command.startsWith("todo")) {
            try {
                des = command.substring(5);
                toBeAdded = new Todo(des);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("todo");
            }
        } else if (command.startsWith("deadline")) {
            try {
                tokens = command.split(" /by ");
                des = tokens[0].substring(9);
                toBeAdded = new Deadline(des, tokens[1]);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyByException();
            }
        } else if (command.startsWith("event")) {
            try {
                tokens = command.split(" /at ");
                des = tokens[0].substring(6);
                toBeAdded = new Event(des, tokens[1]);
                return new AddCommand(toBeAdded);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeEmptyDescriptionException("event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeEmptyAtException();
            }
        } else {
            throw new DukeUnknownInputException();
        }
    }

    public static Command markAsDone(String command) throws DukeEmptyIndexException {
        try {
            int index;
            String[] tokens = command.split(" ");
            index = Integer.parseInt(tokens[1]);
            return new DoneCommand(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyIndexException("done");
        }
    }

    public static Command delete(String command) throws DukeEmptyIndexException {
        int index;
        try {
            String[] tokens = command.split(" ");
            index = Integer.parseInt(tokens[1]);
            return new DeleteCommand(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyIndexException("delete");
        }
    }

    public static Command parse(String command) throws DukeEmptyIndexException,
            DukeEmptyDescriptionException, DukeEmptyAtException,
            DukeEmptyByException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done")) {
            return markAsDone(command);
        } else if (command.startsWith("delete")) {
            return delete(command);
        } else {
            try {
                return add(command);
            } catch (DukeUnknownInputException e) {
                return new UnknownCommand();
            }
        }
    }
}
