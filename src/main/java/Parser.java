import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    
    private static int getTaskIndex(String input, String command) throws DukeException {
        int indexPosition = command.equals("done") ? 5 : command.equals("delete") ? 7 : 0;
        
        if (input.equals(command) || input.equals(command + " ")) {
            throw new InvalidTaskIndexException(input);
        } else if (input.startsWith(command + " ") && input.length() > indexPosition) {
            try {
                return Integer.parseInt(input.substring(indexPosition));
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException(input);
            }
        } else {
            throw new DukeException();
        }
    }
    
    private static DoneCommand done(String input) throws DukeException {
        return new DoneCommand(getTaskIndex(input, "done"));
    }

    private static DeleteCommand delete(String input) throws DukeException {
        return new DeleteCommand(getTaskIndex(input, "delete"));
    }
    
    private static FindCommand find(String input) throws DukeException {
        if (input.equals("find") || input.equals("find ")) {
            throw new EmptyDescriptionException("find");
        } else if (input.startsWith("find ") && input.length() > 5) {
            return new FindCommand(input.substring(5));
        } else {
            throw new DukeException();
        }
    }

    private static AddCommand newTodo(String input) throws DukeException {
        if (input.equals("todo") || input.equals("todo ")) {
            throw new EmptyDescriptionException("todo");
        } else if (input.startsWith("todo ") && input.length() > 5) {
            Todo newTodo = new Todo(input.substring(5));
            return new AddCommand(newTodo);
        } else {
            throw new DukeException();
        }
    }

    private static AddCommand newDeadline(String input) throws DukeException {
        if (input.equals("deadline") || input.equals("deadline ")) {
            throw new EmptyDescriptionException("deadline");
        } else if (input.startsWith("deadline ") && input.length() > 9) {
            if (!input.contains(" /by ")) {
                throw new InvalidDateTimeException("deadline");
            } else {
                int index = input.indexOf(" /by ");
                String description = input.substring(9, index);
                String date = input.substring(index + 5);
                try {
                    LocalDate deadlineDate = LocalDate.parse(date);
                    Deadline newDeadline = new Deadline(description, deadlineDate);
                    return new AddCommand(newDeadline);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("deadline");
                }
            }
        } else {
            throw new DukeException();
        }
    }

    private static AddCommand newEvent(String input) throws DukeException {
        if (input.equals("event") || input.equals("event ")) {
            throw new EmptyDescriptionException("event");
        } else if (input.startsWith("event ") && input.length() > 6) {
            if (!input.contains(" /at ")) {
                throw new InvalidDateTimeException("event");
            } else {
                int index = input.indexOf(" /at ");
                String description = input.substring(6, index);
                String date = input.substring(index + 5);
                try {
                    LocalDate eventDate = LocalDate.parse(date);
                    Event newEvent = new Event(description, eventDate);
                    return new AddCommand(newEvent);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("event");
                }
            }
        } else {
            throw new DukeException();
        }
    }

    static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list") || input.equals("list ")) {
            return new ListCommand();
        } else {
            if (input.startsWith("done")) {
                return done(input);
            } else if (input.startsWith("delete")) {
                return delete(input);
            } else if (input.startsWith("find")) {
                return find(input);
            } else if (input.startsWith("todo")) {
                return newTodo(input);
            } else if (input.startsWith("deadline")) {
                return newDeadline(input);
            } else if (input.startsWith("event")) {
                return newEvent(input);
            } else {
                throw new DukeException();
            }
        }
    }

}
