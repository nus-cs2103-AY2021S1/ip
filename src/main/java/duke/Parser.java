package duke;

import java.io.InputStream;
import java.util.Scanner;

public class Parser {
    private Scanner sc;

    public Parser(InputStream input) {
        sc = new Scanner(input);
    }

    public ParsedCommand parseNextCommand() throws DukeException {
        String commandType = sc.next();
        ParsedCommand command = new ParsedCommand(commandType);
        int index;
        String name;
        String date;

        switch (commandType) {
        case "bye":
        case "list":
            break;
        case "done":
        case "delete":
            command.withIndex(sc.nextInt() - 1); // UI shows base 1, TaskList uses base 0
            break;
        case "todo":
            command.withName(sc.nextLine().trim());
            break;
        case "deadline":
            try {
                String[] nameAndDeadline = sc.nextLine().trim().split(" /by ");
                command.withName(nameAndDeadline[0]);
                command.withDate(nameAndDeadline[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use the format: deadline <name> /by <yyyy-mm-dd>");
            }
            break;
        case "event":
            try {
                String[] nameAndEvent = sc.nextLine().trim().split(" /at ");
                command.withName(nameAndEvent[0]);
                command.withDate(nameAndEvent[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use the format: event <name> /at <yyyy-mm-dd>");
            }
            break;
        default:
            throw new DukeException("What's that? Please mention one of \"list\", \"done\", \"todo\", " +
                    "\"deadline\", \"event\", or \"bye\".");
        }

        return command;
    }
}
