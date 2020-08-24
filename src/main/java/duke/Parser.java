package duke;

import duke.CommandHandler;
import duke.TaskList;

import java.util.Scanner;

public class Parser {
    public static void parse(TaskList tasks) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                CommandHandler.commandHandler(input, DukeCommandType.EXIT, tasks);
                break;
            } else if (input.equals("help")) {
                CommandHandler.commandHandler(input, DukeCommandType.HELP, tasks);
            } else if (input.equals("list")) {
                CommandHandler.commandHandler(input, DukeCommandType.LIST, tasks);
            } else if (input.startsWith("todo")) {
                CommandHandler.commandHandler(input, DukeCommandType.TODO, tasks);
            } else if (input.startsWith("deadline")) {
                CommandHandler.commandHandler(input, DukeCommandType.DEADLINE, tasks);
            } else if (input.startsWith("event")) {
                CommandHandler.commandHandler(input, DukeCommandType.EVENT, tasks);
            } else if (input.startsWith("done")) {
                CommandHandler.commandHandler(input, DukeCommandType.DONE, tasks);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                CommandHandler.commandHandler(input, DukeCommandType.DELETE, tasks);
                input = sc.nextLine();
                continue;
            } else {
                CommandHandler.commandHandler(input, DukeCommandType.UNKNOWN, tasks);
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
    }
}
