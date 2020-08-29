package botbot;

import botbot.commands.AddCommand;
import botbot.commands.Command;
import botbot.commands.DeleteCommand;
import botbot.commands.ExitCommand;
import botbot.commands.InvalidCommand;
import botbot.commands.ListCommand;
import botbot.commands.MarkAsDoneCommand;
import botbot.exceptions.EmptyTaskException;
import botbot.exceptions.EmptyTaskNumberException;
import botbot.exceptions.InvalidFormatException;
import botbot.exceptions.NoSuchCommandException;
import botbot.tasks.Deadline;
import botbot.tasks.Event;
import botbot.tasks.Todo;

public class Parser {
    static Command parseCommand(String input) throws EmptyTaskException, EmptyTaskNumberException,
            InvalidFormatException, NoSuchCommandException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.length() >= 8 && input.substring(0, 7).equals("delete ")) {
            try {
                int id = parseCommandId(input, "delete ");
                return new DeleteCommand(id);
            } catch (NumberFormatException e) {
                return new InvalidCommand("    oops! invalid task number!\n");
            }
        } else if (input.equals("delete") || (input.length() >= 7 && input.substring(0, 7).equals("delete "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("done ")) {
            try {
                int id = parseCommandId(input, "done ");
                return new MarkAsDoneCommand(id);
            } catch (NumberFormatException e) {
                return new InvalidCommand("    oops! invalid task number!\n");
            }
        } else if (input.equals("done") || (input.length() >= 5 && input.substring(0, 5).equals("done "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("todo ")) {
            return new AddCommand(new Todo(input));
        } else if (input.equals("todo") || (input.length() >= 5 && input.substring(0, 5).equals("todo "))) {
            throw new EmptyTaskException("a todo");
        } else if (input.length() >= 10 && input.substring(0, 9).equals("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException(Deadline.FORMAT);
            }
            return new AddCommand(new Deadline(input));
        } else if (input.equals("deadline")
                || (input.length() >= 9 && input.substring(0, 9).equals("deadline "))) {
            throw new EmptyTaskException("a deadline");
        } else if (input.length() >= 7 && input.substring(0, 6).equals("event ")) {
            if (!input.contains(" /at ")) {
                throw new InvalidFormatException(Event.FORMAT);
            }
            return new AddCommand(new Event(input));
        } else if (input.equals("event")
                || (input.length() >= 6 && input.substring(0, 6).equals("event "))) {
            throw new EmptyTaskException("an event");
        } else {
            throw new NoSuchCommandException();
        }
    }
    
    static int parseCommandId(String input, String command) {
        return Integer.parseInt(input.substring(command.length())) - 1;
    }
}
