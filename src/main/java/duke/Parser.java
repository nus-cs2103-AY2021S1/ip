package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.AddNoteCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DeleteNoteCommand;
import duke.command.DescriptionCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ListNoteCommand;
import duke.note.Note;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

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
        // a bye input
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }

        // a list input
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        // a listnote input
        if (fullCommand.equals("listnote")) {
            return new ListNoteCommand();
        }

        String[] test = fullCommand.split(" ");

        // a note input
        if (test[0].equals("note")) {
            if (test.length == 1) {
                throw new DukeException("The title of the note cannot be empty! ");
            }
            String[] str = fullCommand.split("note ")[1].split("/d");
            if (str.length == 1) {
                throw new DukeException("The description of the note cannot be empty! ");
            }
            String title = str[0];
            assert title != null : "Missing title! ";
            String description = str[1];
            assert description != null : "Missing description! ";
            Note note = new Note(title, description);
            return new AddNoteCommand(note);
        }

        // a delete note input
        if (test[0].equals("delnote")) {
            if (test.length == 1) {
                throw new DukeException("Please select the note you want to delete!");
            }
            int idx = Integer.parseInt(test[1]);
            assert idx > 0 : "Index is not valid! ";
            return new DeleteNoteCommand(idx);
        }

        // a description input
        if (test[0].equals("description")) {
            if (test.length == 1) {
                throw new DukeException("Please select the note you want!");
            }
            int idx = Integer.parseInt(test[1]);
            assert idx > 0 : "Index is not valid! ";
            return new DescriptionCommand(idx);
        }

        // a done input
        if (test[0].equals("done")) {
            if (test.length == 1) {
                throw new DukeException("Please select the task that you want to mark done!");
            }
            int idx = Integer.parseInt(test[1]);
            assert idx > 0 : "Index is not valid! ";
            return new DoneCommand(idx);
        }

        // a delete input
        if (test[0].equals("delete")) {
            if (test.length == 1) {
                throw new DukeException("Please select the task that you want to delete!");
            }
            int idx = Integer.parseInt(test[1]);
            assert idx > 0 : "Index is not valid! ";
            return new DeleteCommand(idx);
        }

        //a find input
        if (test[0].equals("find")) {
            if (test.length == 1) {
                throw new DukeException("Please include the keyword!");
            }
            String keyword = test[1];
            assert keyword != null : "Missing keyword! ";
            return new FindCommand(keyword);
        }

        // a todo input
        if (test[0].equals("todo")) {
            try {
                String description = fullCommand.split("todo ")[1];
                assert description != null : "Missing description! ";
                Todo todo = new Todo(description);
                return new AddCommand(todo);
            } catch (ArrayIndexOutOfBoundsException err) {
                MissingDescriptionException realError = new MissingDescriptionException("a todo");
                throw new DukeException(realError.getMessage());
            }
        }

        // a deadline input
        if (test[0].equals("deadline")) {
            try {
                String[] str = fullCommand.split("deadline ")[1].split(" /");
                if (str.length == 1) {
                    throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                }
                try {
                    String description = str[0];
                    assert description != null : "Missing description! ";
                    String by = str[1].split("by ")[1];
                    assert by != null : "Missing deadline! ";
                    LocalDateTime byTime = LocalDateTime.parse(by);
                    Deadline deadline = new Deadline(description, byTime);
                    return new AddCommand(deadline);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Format of date and time is incorrect! "
                            + "Please fill in the date and time following the format below. \n"
                            + "       YYYY-MM-DDTHH:MM");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                MissingDescriptionException realError = new MissingDescriptionException("a deadline");
                throw new DukeException(realError.getMessage());
            }
        }

        // an event input
        if (test[0].equals("event")) {
            try {
                String[] str = fullCommand.split("event ")[1].split(" /at ");
                if (str.length == 1) {
                    throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
                }
                try {
                    String description = str[0];
                    assert description != null : "Missing description! ";
                    String at = str[1];
                    assert at != null : "Missing time! ";
                    Event event = new Event(description, LocalDateTime.parse(at));
                    return new AddCommand(event);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Format of date and time is incorrect! "
                            + "Please fill in the date and time following the format below. \n"
                            + "       YYYY-MM-DDTHH:MM");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                MissingDescriptionException realError = new MissingDescriptionException("an event");
                throw new DukeException(realError.getMessage());
            }
        }

        // the input is unknown
        UnknownInputException ue = new UnknownInputException();
        throw new DukeException(ue.getMessage());


    }

}
