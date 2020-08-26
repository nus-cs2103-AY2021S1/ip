package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Command parse(String input) throws DukeException {
        String[] inputBreakdown = input.split(" ", 2);
        String command = inputBreakdown[0];
        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("done"):
            if (inputBreakdown.length < 2) {
                throw new DukeException("Error! Note the syntax: done [task number]");
            } else {
                String taskNumber = inputBreakdown[1];
                return new DoneCommand(taskNumber);
            }
        case ("todo"):
            if (inputBreakdown.length < 2) {
                throw new DukeException("Error! Note the syntax: todo [description]");
            } else {
                String description = inputBreakdown[1];
                return new TodoCommand(description);
            }
        case ("deadline"):
            if (inputBreakdown.length < 2) {
                throw new DukeException("Error! Note the syntax: deadline [description] /by [date]");
            } else {
                String[] deadlineBreakdown = inputBreakdown[1].split(" /by ", 2);
                if (deadlineBreakdown.length < 2) {
                    throw new DukeException("Error! Note the syntax: deadline [description] /by [date]");
                } else {
                    String description = deadlineBreakdown[0];
                    String by = deadlineBreakdown[1];
                    return new DeadlineCommand(description, parseDateTime(by));
                }
            }
        case ("event"):
            if (inputBreakdown.length < 2) {
                throw new DukeException("Error! Note the syntax: event [description] /at [date]");
            } else {
                String[] eventBreakdown = inputBreakdown[1].split(" /at ", 2);
                if (eventBreakdown.length < 2) {
                    throw new DukeException("Error! Note the syntax: event [description] /at [date]");
                } else {
                    String description = eventBreakdown[0];
                    String at = eventBreakdown[1];
                    return new EventCommand(description, parseDateTime(at));
                }
            }
        case ("delete"):
            if (inputBreakdown.length < 2) {
                throw new DukeException("Error! Note the syntax: delete [task number]");
            } else {
                String taskNumber = inputBreakdown[1];
                return new DeleteCommand(taskNumber);
            }
        case ("bye"):
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    public LocalDateTime parseDateTime(String input) throws DukeException {
        String pattern = "d/M/yyyy HHmm";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            throw new DukeException("Error! Note the date format: " + pattern);
        }
    }
}
