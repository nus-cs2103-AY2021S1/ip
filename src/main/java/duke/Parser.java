package duke;

import duke.commands.*;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public String commandLine;

    Parser(String commandLine) {
        this.commandLine = commandLine;
    }

    public static Command parse(String commandLine) throws DukeException {
        try {
            if (isList(commandLine)) {
                return new ListCommand();
            } else if (isToDo(commandLine)) {
                if (commandLine.equals("todo")) {
                    throw new DukeEmptyToDoException();
                }
                ToDo task = new ToDo(commandLine.substring(5), false);
                return new AddCommand(task);

            } else if (isDeadline(commandLine)) {
                if (commandLine.equals("deadline")) {
                    throw new DukeEmptyDeadlineException();
                }
                String[] arrOfString = (commandLine.substring(9)).split("/by ", 2);
                if (arrOfString.length == 1) {
                    throw new DukeDeadlineFormatException();
                }
                String deadline = arrOfString[1];
                String description = arrOfString[0];
                Deadline task = new Deadline(description, false, LocalDate.parse(deadline));
                return new AddCommand(task);

            } else if (isEvent(commandLine)) {
                if (commandLine.equals("event")) {
                    throw new DukeEmptyEventException();
                }
                String[] arrOfString = (commandLine.substring(6)).split("/at ", 2);

                if (arrOfString.length == 1) {
                    throw new DukeEventFormatException();
                }
                String eventDate = arrOfString[1];
                String description = arrOfString[0];
                Event task = new Event(description, false, LocalDate.parse(eventDate));
                return new AddCommand(task);

            } else if (isDone(commandLine) || isDelete(commandLine)) {
                String[] tokens = commandLine.split(" ");
                int num = Integer.parseInt(tokens[1]);

                if (isDone(commandLine)) {
                    return new DoneCommand(num - 1);
                } else {
                    return new DeleteCommand(num - 1);
                }
            } else if (commandLine.equals("bye")) {
                return new ExitCommand();

            } else {
                throw new DukeInvalidTaskException();
            }
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyActionException();
        }
    }

    static boolean isList(String string) {
        return string.equals("list");
    }

    static boolean isToDo(String string) {
        return string.startsWith("todo");
    }

    static boolean isDeadline(String string) {
        return string.startsWith("deadline");
    }

    static boolean isEvent(String string) {
        return string.startsWith("event");
    }

    static boolean isDone(String string) {
        return string.startsWith("done");
    }

    static boolean isDelete(String string) {
        return string.startsWith("delete");
    }
}
