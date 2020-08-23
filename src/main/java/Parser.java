package main.java;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DoNothingCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.TasksOnCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser which helps make any sense of the input from the user.
 */
public class Parser {

    /**
     * Converts the input from the user into a Command understandable by Duke.
     *
     * @param command String input from user.
     * @return A Command that Duke can execute.
     * @throws DukeException Thrown when input from user is invalid.
     */
    public static Command parse(String command) throws DukeException {
        String input = command.trim();

        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.startsWith("done")) {
            if (input.length() == 4 || input.substring(5).isBlank()) {
                throw new DukeException("\tNo task number specified.");
            }
            int idx;
            try {
                idx = Integer.parseInt(input.substring(5).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("\tTask number format invalid, "
                        + "must be a number.");
            }
            return new DoneCommand(idx);
        } else if (input.startsWith("delete")) {
            if (input.length() == 6 || input.substring(7).isBlank()) {
                throw new DukeException("\tNo task number specified.");
            }
            int idx;
            try {
                idx = Integer.parseInt(input.substring(7).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("\tTask number format invalid, "
                        + "must be a number.");
            }
            return new DeleteCommand(idx);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            if (input.length() == 4 || input.substring(5).isBlank()) {
                throw new DukeException("\tNo keyword specified.");
            }

            return new FindCommand(input.substring(5).trim());
        } else if (input.startsWith("tasks on")) {
            if (input.length() == 8 || input.substring(9).isBlank()) {
                throw new DukeException("\tNeed to specify the date of the tasks");
            }
            String dateOn = input.substring(9).trim();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date;
            try {
                date = LocalDate.parse(dateOn, format);
            } catch (DateTimeParseException e) {
                throw new DukeException("\tDate should be in format dd/mm/yyyy");
            }

            return new TasksOnCommand(date);
        } else if (!input.isBlank()) {
            Task newTask;
            String desc;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime time;

            if (input.startsWith("todo")) {
                if (input.length() == 4 || input.substring(5).isBlank()) {
                    throw new DukeException("\tThe description of a todo cannot be empty.");
                }
                desc = input.substring(5).trim();
                newTask = new Todo(desc);
            } else if (input.startsWith("deadline")) {
                String[] components = input.split(" /by ");
                if (components[0].length() == 8 || components[0].substring(9).isBlank()) {
                    throw new DukeException("\tThe description of a deadline cannot be empty.");
                }
                if (components.length == 1 || components[1].isBlank()) {
                    throw new DukeException("\tThe date of a deadline cannot be empty.");
                }
                desc = components[0].substring(9).trim();
                try {
                    time = LocalDateTime.parse(components[1].trim(), format);
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tDate should be in format dd/mm/yyyy hh:mm");
                }
                newTask = new Deadline(desc, time);
            } else if (input.startsWith("event")) {
                String[] components = input.split(" /at ");
                if (components[0].length() == 5 || components[0].substring(6).isBlank()) {
                    throw new DukeException("\tThe description of an event cannot be empty.");
                }
                if (components.length == 1 || components[1].isBlank()) {
                    throw new DukeException("\tThe date of an event cannot be empty.");
                }
                desc = components[0].substring(6).trim();
                try {
                    time = LocalDateTime.parse(components[1].trim(), format);
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tDate should be in format dd/mm/yyyy hh:mm");
                }
                newTask = new Event(desc, time);
            } else {
                throw new DukeException("\tI don't know what that means :-(");
            }

            return new AddCommand(newTask);
        }

        return new DoNothingCommand();
    }
}
