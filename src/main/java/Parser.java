import command.*;
import exception.DukeException;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

/**
 * Encapsulates parser into a class. The class supports parsing the command messages
 * from the user into different commands.
 */
public class Parser {
    public static Command parse(String commandMessage) throws DukeException {
        Command command;
        if (commandMessage.equals("list")) {
            command = new ListCommand("list");
        } else if (commandMessage.contains("done")) {
            int order = parseInt(commandMessage.substring(commandMessage.length() - 1));
            command = new DoneCommand("done", order);
        } else if (commandMessage.contains("delete")) {
            int order = parseInt(commandMessage.substring(commandMessage.indexOf(' ') + 1));
            command = new DeleteCommand("delete", order);
        } else if (commandMessage.contains("find")) {
            command = new FindCommand("find", commandMessage.substring(commandMessage.indexOf(' ') + 1));
        } else if (commandMessage.equals("bye")) {
            command = new ByeCommand("bye");
        } else if (commandMessage.contains("priority")) {
            String message = commandMessage.substring(commandMessage.indexOf(' ') + 1);
            int order = parseInt(message.substring(0, message.indexOf(' ')));
            int priority = parseInt(message.substring(message.indexOf(' ') + 1));
            command = new PriorityCommand("priority", order, priority);
        } else {
            command = parseTask(commandMessage);
        }
            return command;

    }

    public static Command parseTask(String commandMessage) throws DukeException {
        String type;
        Command command;
        if (commandMessage.contains(" ")) {
            type = commandMessage.substring(0, commandMessage.indexOf(' '));
        } else {
            String str = "";
            switch (commandMessage) {
            case "todo":
                str = "☹ OOPS!!! The description of a todo cannot be empty.";
                break;
            case "deadline":
                str = "☹ OOPS!!! The description of a deadline cannot be empty.";
                break;
            case "event":
                str = "☹ OOPS!!! The description of an event cannot be empty.";
                break;
            default:
                str = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
            throw new DukeException(str);
        }

        if (type.equals("deadline")) {
            command = parseDeadline(commandMessage);
        } else if (type.equals("event")) {
            command = parseEvent(commandMessage);
        } else if (type.equals("todo")) {
            command = parseTodo(commandMessage);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }

    public static Command parseDeadline(String commandMessage) throws DukeException {
        Command command;
        if (!commandMessage.contains("/by")) {
            throw new DukeException("Deadline command should contain \"/by\".");
        }

        String description = commandMessage.substring(commandMessage.indexOf(' ') + 1,
                commandMessage.indexOf('/') - 1);
        String by = commandMessage.substring(commandMessage.indexOf("/by") + 4);

        String s = "";

        if (by.charAt(0) >= '0' && by.charAt(0) <= '9') {
            if (by.charAt(1) == '/' || by.charAt(2) == '/') {
                int firstSlashIndex = by.indexOf('/');
                int secondSlashIndex = by.indexOf('/', firstSlashIndex + 1);

                String day = by.substring(0, firstSlashIndex);
                String month = by.substring(firstSlashIndex + 1, secondSlashIndex);
                String year = by.substring(secondSlashIndex + 1, secondSlashIndex + 5);

                day = day.length() == 2 ? day : '0' + day;
                month = month.length() == 2 ? month : '0' + month;

                s = year + '-' + month + '-' + day;
            } else if (by.charAt(4) == '-') {
                int firstDashIndex = by.indexOf('-');
                int secondDashIndex = by.indexOf('-', firstDashIndex + 1);

                String year = by.substring(0, firstDashIndex);
                String month = by.substring(firstDashIndex + 1, secondDashIndex);
                String day = by.substring(secondDashIndex + 1);

                day = day.contains(" ") ? day.substring(0, day.indexOf(' ')) : day;

                day = day.length() == 1 ? '0' + day : day.substring(0, 2);
                month = month.length() == 2 ? month : '0' + month;

                s = year + '-' + month + '-' + day;
            }
            LocalDate date = LocalDate.parse(s);
            command = new DeadlineCommand("deadline", description, by, date, true);
        } else {
            command = new DeadlineCommand("deadline", description, by, null, false);
        }

        return command;
    }

    public static Command parseEvent(String commandMessage) throws DukeException {
        Command command;
        if (!commandMessage.contains("/at")) {
            throw new DukeException("Event command should contain \"/at\".");
        }

        String description = commandMessage.substring(commandMessage.indexOf(' ') + 1,
                commandMessage.indexOf('/') - 1);
        String time = commandMessage.substring(commandMessage.indexOf("/at") + 4);
        String s = "";

        if (time.charAt(0) >= '0' && time.charAt(0) <= '9') {
            if (time.charAt(1) == '/' || time.charAt(2) == '/') {
                int firstSlashIndex = time.indexOf('/');
                int secondSlashIndex = time.indexOf('/', firstSlashIndex + 1);

                String day = time.substring(0, firstSlashIndex);
                String month = time.substring(firstSlashIndex + 1, secondSlashIndex);
                String year = time.substring(secondSlashIndex + 1, secondSlashIndex + 5);

                day = day.length() == 2 ? day : '0' + day;
                month = month.length() == 2 ? month : '0' + month;

                s = year + '-' + month + '-' + day;
            } else if (time.charAt(4) == '-') {
                int firstDashIndex = time.indexOf('-');
                int secondDashIndex = time.indexOf('-', firstDashIndex + 1);

                String year = time.substring(0, firstDashIndex);
                String month = time.substring(firstDashIndex + 1, secondDashIndex);
                String day = time.substring(secondDashIndex + 1);

                day = day.contains(" ") ? day.substring(0, day.indexOf(' ')) : day;

                day = day.length() == 1 ? '0' + day : day.substring(0, 2);
                month = month.length() == 2 ? month : '0' + month;

                s = year + '-' + month + '-' + day;
            }
            LocalDate date = LocalDate.parse(s);
            command = new EventCommand("event", description, time, date, true);
        } else {
            command = new EventCommand("event", description, time, null, false);
        }

        return command;
    }

    public static Command parseTodo(String commandMessage) throws DukeException {
        Command command;
        String description = commandMessage.substring(commandMessage.indexOf(' ') + 1);
        command = new ToDoCommand("todo", description, "", null, false);
        return command;
    }
}

