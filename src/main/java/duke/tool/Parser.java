package duke.tool;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import duke.exception.AmbiguousInputException;
import duke.exception.DeletionEmptyException;
import duke.exception.DescriptionEmptyException;
import duke.exception.DukeException;

import duke.exception.TimeEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter acceptedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("")) {
            throw new AmbiguousInputException();
        }

        String[] s = input.split(" ", 2);
        if (s[0].equals("list")) {
            return new ListCommand();
        } else if (s[0].equals("done")) {
            int index = Integer.parseInt(s[1]) - 1;
            return new DoneCommand(index);
        } else {
            if (s.length == 1) {
                if (s[0].equals("delete")) {
                    throw new DeletionEmptyException();
                } else {
                    throw new DescriptionEmptyException(s[0]);
                }
            }

            //Judge the action and execute
            switch (s[0]) {
            case "todo":
                return new AddCommand(new Todo(s[1]));
            case "deadline": {
                String[] set = s[1].split(" /by ");

                if (set.length == 1) {
                    throw new TimeEmptyException("deadline");
                }

                return new AddCommand(new Deadline(set[0]
                        , LocalDateTime.parse(set[1], acceptedFormatter)));
            }
            case "event": {
                String[] set = s[1].split(" /at ");

                if (set.length == 1) {
                    throw new TimeEmptyException("event");
                }

                return new AddCommand(new Event(set[0], LocalDateTime.parse(set[1], acceptedFormatter)));
            }
            case "delete": {
                return new DeleteCommand(Integer.parseInt(s[1]) - 1);
            }
            default:
                throw new AmbiguousInputException();
            }
        }
    }
}
