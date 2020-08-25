package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.ToDo;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.toLowerCase();
        String[] fullCommandArray = fullCommand.split(" ");
        fullCommand = fullCommand.strip();
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("show")) {
            throw new InvalidArgumentException("☹ OOPS!!! The show command requires a date in yyyy-mm-dd.");
        } else if (fullCommand.equals("find")) {
            throw new InvalidArgumentException("☹ OOPS!!! The find command requires keyword.");
        } else if (fullCommand.equals("delete")) {
            throw new InvalidCommandException("☹ OOPS!!! The delete command requires the index of a task.");
        } else if (fullCommand.equals("done")) {
            throw new InvalidCommandException("☹ OOPS!!! The done command requires the index of a task.");
        } else if (fullCommandArray[0].equals("list")) {
            throw new InvalidArgumentException("☹ OOPS!!! The list command does not take any additional argument(s).");
        } else if (fullCommandArray[0].equals("bye")) {
            throw new InvalidArgumentException("☹ OOPS!!! The bye command does not take any additional argument(s).");
        } else if (fullCommandArray[0].equals("show")) {
            try {
                LocalDate date = LocalDate.parse(fullCommandArray[1]);
                return new ShowCommand(date);
            } catch (DateTimeParseException e) {
                throw new InvalidArgumentException("☹ OOPS!!! The show command requires a date in yyyy-mm-dd.");
            }
        } else if (fullCommandArray[0].equals("done")) {
            return new DoneCommand(Integer.parseInt(fullCommandArray[1]));
        } else if (fullCommandArray[0].equals("delete")) {
            return new DeleteCommand(Integer.parseInt(fullCommandArray[1]));
        } else if (fullCommandArray[0].equals("find")) {
            return new FindCommand(fullCommandArray[1]);
        } else {
            String type = fullCommand.split(" ")[0];
            String temp = fullCommand.strip();
            if (temp.equals(TaskType.TODO.getType()) || temp.equals(TaskType.DEADLINE.getType()) || temp.equals(TaskType.EVENT.getType())) {
                throw new InvalidArgumentException("☹ OOPS!!! The description of " + (temp.equals(TaskType.EVENT.getType()) ? "an " : "a ") + temp + " cannot be empty.");
            } else if (temp.equals("")) {
                throw new InvalidTaskTypeException("☹ OOPS!!! The type of a task cannot be empty.");
            }
            if (type == null || (!type.equals(TaskType.TODO.getType()) && !type.equals(TaskType.DEADLINE.getType()) && !type.equals(TaskType.EVENT.getType()))) {
                throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String details = fullCommand.substring(type.length());
            if (type.equals(TaskType.TODO.getType())) {
                ToDo t = new ToDo(details.strip());
                return new AddCommand(t);
            } else if (type.equals(TaskType.DEADLINE.getType())) {
                String[] detailsArray = details.split("/by");
                if (detailsArray.length <= 1) {
                    throw new InvalidArgumentException("☹ OOPS!!! Please specify a due date for the deadline.");
                }
                LocalDate date;
                try {
                    date = LocalDate.parse(detailsArray[1].strip());
                } catch (DateTimeParseException e) {
                    throw new InvalidArgumentException("☹ OOPS!!! Please enter a date in yyyy-mm-dd format.");
                }
                Deadline d = new Deadline(detailsArray[0].strip(), date);
                return new AddCommand(d);
            } else {
                String[] detailsArray = details.split("/at");
                if (detailsArray.length <= 1) {
                    throw new InvalidArgumentException("☹ OOPS!!! Please specify a date for the event.");
                }
                LocalDate date;
                try {
                    date = LocalDate.parse(detailsArray[1].strip());
                } catch (DateTimeParseException e) {
                    throw new InvalidArgumentException("☹ OOPS!!! Please enter a date in yyyy-mm-dd format.");
                }
                Event e = new Event(detailsArray[0].strip(), date);
                return new AddCommand(e);
            }
        }
    }
}
