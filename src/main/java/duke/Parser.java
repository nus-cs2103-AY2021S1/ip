package duke;

import duke.commands.*;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

            } else if (isDeadline(commandLine) || isEvent(commandLine)) {
                if (commandLine.equals("deadline")) {
                    throw new DukeEmptyDeadlineException();
                } else if (commandLine.equals("event")) {
                    throw new DukeEmptyEventException();
                }


                String[] arrOfString;
                if (isDeadline(commandLine)) {
                    arrOfString = (commandLine.substring(9)).split("/by ", 2);
                    if (arrOfString.length == 1) {
                        throw new DukeDeadlineFormatException();
                    }
                } else {
                    arrOfString = (commandLine.substring(6)).split("/at ", 2);
                    if (arrOfString.length == 1) {
                        throw new DukeEventFormatException();
                    }
                }

                if (arrOfString.length != 2 ) {
                    throw new DukeDateTimeParseException();
                }

                // Format of LocalDateTime is "yyyy-MM-dd HH:mm"
                String description = arrOfString[0];
                LocalDateTime dateAndTime = parseDateTime(arrOfString[1]);
                Task task;
                if (isDeadline(commandLine)) {
                    task = new Deadline(description, false, dateAndTime);
                } else {
                    task = new Event(description, false, dateAndTime);
                }
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

    static LocalDateTime parseDateTime(String commandLine) {
        String[] dateNumbers = commandLine.split("-");
        //Represents 'dd hh:mm'
        String[] dayAndTime = dateNumbers[2].split(" ");
        //Represents 'yyyy'
        int year = Integer.parseInt(dateNumbers[0]);
        //Represents 'MM'
        Integer month = Integer.parseInt(dateNumbers[1]);
        //Represents 'dd'
        Integer day = Integer.parseInt(dayAndTime[0]);

        //Represents 'hh:mm'
        String time = dayAndTime[1];
        String[] timeNumbers = time.split(":");
        Integer hour = Integer.parseInt(timeNumbers[0]);
        Integer minutes = Integer.parseInt(timeNumbers[1]);

        return LocalDateTime.of(year, month, day, hour, minutes);
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
