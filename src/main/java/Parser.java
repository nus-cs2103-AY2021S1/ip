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
            command = new Command("list");
        } else if (commandMessage.contains("done")) {
            int order = parseInt(commandMessage.substring(commandMessage.length() - 1));
            command = new Command("done", order);
        } else if (commandMessage.contains("delete")) {
            int order = parseInt(commandMessage.substring(commandMessage.length() - 1));
            command = new Command("delete", order);
        } else if (commandMessage.contains("find")) {
            command = new Command("find", commandMessage.substring(commandMessage.indexOf(' ') + 1));
        } else if (commandMessage.equals("bye")) {
            command = new Command("bye");
        } else {
            String type;
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
                String description = commandMessage.substring(commandMessage.indexOf(' ') + 1,
                        commandMessage.indexOf('/') - 1);
                String by = commandMessage.substring(commandMessage.indexOf("/by") + 4);

                String s = "";

                if (by.charAt(0) >= '0' && by.charAt(0) <= '9') {
                    if (by.charAt(1) == '/') {
                        s = s + by.substring(5, 9);
                        s = s + '-';
                        s = s + by.substring(2, 4);
                        s = s + '-';
                        s = s + '0' + by.substring(0,1);
                    } else {
                        s = s + by.substring(6, 10);
                        s = s + '-';
                        s = s + by.substring(3, 5);
                        s = s + '-';
                        s = s + '0' + by.substring(0, 2);
                    }
                    LocalDate date = LocalDate.parse(s);
                    command = new Command("deadline", description, by, date, true);
                } else {
                    command = new Command("deadline", description, by, null, false);
                }

            } else if (type.equals("event")) {
                String time = commandMessage.substring(commandMessage.indexOf("/at") + 4);
                String description = commandMessage.substring(commandMessage.indexOf(' ') + 1,
                        commandMessage.indexOf('/') - 1);

                String s = "";

                if (time.charAt(0) >= '0' && time.charAt(0) <= '9') {
                    if (time.charAt(1) == '/') {
                        s = s + time.substring(5, 9);
                        s = s + '-';
                        s = s + time.substring(2, 4);
                        s = s + '-';
                        s = s + '0' + time.substring(0,1);
                    } else {
                        s = s + time.substring(6, 10);
                        s = s + '-';
                        s = s + time.substring(3, 5);
                        s = s + '-';
                        s = s + '0' + time.substring(0, 2);
                    }
                    LocalDate date = LocalDate.parse(s);
                    command = new Command("event", description, time, date, true);
                } else {
                    command = new Command("event", description, time, null, false);
                }
            } else if (type.equals("todo")) {
                String description = commandMessage.substring(commandMessage.indexOf(' ') + 1);
                command = new Command("todo", description, "", null, false);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return command;
    }
}
