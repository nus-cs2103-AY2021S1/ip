package duke;

import java.util.Scanner;

public class Parser {
    private Scanner sc;

    public Parser() {
        sc = new Scanner(System.in);
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
            String[] nameAndDeadline = sc.nextLine().split(" /by ");
            command.withName(nameAndDeadline[0]);
            command.withDate(nameAndDeadline[1]);
            break;
        case "event":
            String[] nameAndEvent = sc.nextLine().split(" /at ");
            command.withName(nameAndEvent[0]);
            command.withDate(nameAndEvent[1]);
            break;
        default:
            throw new DukeException("What's that? Please mention one of \"list\", \"done\", \"todo\", \"deadline\", \"event\", or \"bye\".");
        }

        return command;
    }
}
