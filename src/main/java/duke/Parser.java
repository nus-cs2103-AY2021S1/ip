package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Creates a parser that parses the command.
 */
public class Parser {
    /**
     * Parses the command and decides the type of the command.
     * 
     * @param fullCommand A string of the command.
     * @return A command object representing the command.
     * @throws DukeException when the command is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else {
            String[] test = fullCommand.split(" ");
            if (test[0].equals("done")) {
                if (test.length == 1) {
                    throw new DukeException("Please select the task that you want to mark done!");
                }
                int idx = Integer.parseInt(test[1]);
                return new DoneCommand(idx);
            } else if (test[0].equals("delete")) {
                if (test.length == 1) {
                    throw new DukeException("Please select the task that you want to delete!");
                }
                int idx = Integer.parseInt(test[1]);
                return new DeleteCommand(idx);
            } else if (test[0].equals("find")) {
                if (test.length == 1) {
                    throw new DukeException("Please include the keyword!");
                }
                String keyword = test[1];
                return new FindCommand(keyword);
            } else {
                if (test[0].equals("todo")) {
                    try {
                        String description = fullCommand.split("todo ")[1];
                        Todo todo = new Todo(description);
                        return new AddCommand(todo);
                    } catch (ArrayIndexOutOfBoundsException err) {
                        MissingDescriptionException realError = new MissingDescriptionException("a todo");
                        throw new DukeException(realError.getMessage());
                    }
                } else if (test[0].equals("deadline")) {
                    try {
                        String[] str = fullCommand.split("deadline ")[1].split(" /");
                        if (str.length == 1) {
                            throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                        } else {
                            try {
                                String description = str[0];
                                String by = str[1].split("by ")[1];
                                LocalDateTime byTime = LocalDateTime.parse(by);
                                Deadline deadline = new Deadline(description, byTime);
                                return new AddCommand(deadline);
                            } catch (DateTimeParseException e) {
                                throw new DukeException("Format of date and time is incorrect! " 
                                        + "Please fill in the date and time following the format below. \n" 
                                        + "       YYYY-MM-DDTHH:MM:SS");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        MissingDescriptionException realError = new MissingDescriptionException("a deadline");
                        throw new DukeException(realError.getMessage());
                    }
                } else if (test[0].equals("event")) {
                    try {
                        String[] str = fullCommand.split("event ")[1].split(" /at ");
                        if (str.length == 1) {
                            throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
                        } else {
                            try {
                                String description = str[0];
                                String at = str[1];
                                Event event = new Event(description, LocalDateTime.parse(at));
                                return new AddCommand(event);
                            } catch (DateTimeParseException e) {
                                throw new DukeException("Format of date and time is incorrect! Please fill in the date and time following the format below. \n" +
                                        "       YYYY-MM-DDTHH:MM:SS");
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        MissingDescriptionException realError = new MissingDescriptionException("an event");
                        throw new DukeException(realError.getMessage());
                    }
                } else {
                    UnknownInputException ue = new UnknownInputException();
                    throw new DukeException(ue.getMessage());
                }
            }
        }
    }
    
}
