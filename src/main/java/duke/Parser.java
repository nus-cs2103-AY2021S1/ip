package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;

public class Parser {
    
    public static Command parseCommand(String fullCommand) throws DukeException {
        Command command;
        String[] splitCommand = fullCommand.split(" ", 2);
        String action = splitCommand[0];
        String errorMessage = "";
        try {
            if (action.equals("bye")) {
                command = new ExitCommand();
            } else if (action.equals("list")) {
                command = new ListCommand();
            } else if (action.equals("todo")) {
                String description = splitCommand[1];
                command = new TodoCommand(description);
            } else if (action.equals("deadline")) {
                String[] splitDeadline = splitCommand[1].split("/by");
                String description = splitDeadline[0].trim();
                String by = splitDeadline[1].trim();
                LocalDate date = LocalDate.parse(by);
                command = new DeadlineCommand(description, date);
            } else if (action.equals("event")) {
                String[] splitEvent = splitCommand[1].split("/at");
                String description = splitEvent[0].trim();
                String at = splitEvent[1].trim();
                LocalDate date = LocalDate.parse(at);
                command = new EventCommand(description, date);
            } else if (action.equals("done")) {
                String taskNumber = splitCommand[1];
                command = new DoneCommand(taskNumber);
            } else if (action.equals("delete")) {
                String taskNumber = splitCommand[1];
                command = new DeleteCommand(taskNumber);
            } else {
                command = new InvalidCommand();
            }
            return command;
        } catch (ArrayIndexOutOfBoundsException e) {
            if (splitCommand.length <= 1) { // Entered keyword without description/task number
                if (isTask(action)) {
                    errorMessage = "OOPS!!! Description of a task cannot be empty :(\n";
                } else if (action.equals("done") || action.equals("delete")) {
                    errorMessage = "Missing task number! " + "Please ensure to key in the task number :)\n";
                }
            } else { // Deadline/Event missing their respective keywords
                if (action.equals("deadline")) {
                    errorMessage = "Please indicate a deadline using the \"/by\" keyword.\n";
                } else if (action.equals("event")) {
                    errorMessage = "Please indicate a timing using the \"/at\" keyword.\n";
                }
            }
            throw new DukeException(errorMessage);
        } catch (DateTimeParseException e) {
            errorMessage = "Invalid date format! "
                    + "Please use the proper date format i.e. yyyy-MM-dd\n";
            throw new DukeException(errorMessage);
        }
    }

    private static boolean isTask(String action) {
        return action.equals("todo") || action.equals("deadline") || action.equals("event");
    }
}