package duke;

import java.util.Scanner;

/**
 * Parser class will take in user's inputs and make sense of it.
 */
public class Parser {
    /**
     * Interprets the command type from user's input and calls CommandHandler to process the commands.
     *
     * @param tasks
     * @throws DukeException
     */
    public static void parseInputs(TaskList tasks) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                CommandHandler.handleCommands(input, DukeCommandType.EXIT, tasks);
                break;
            } else if (input.equals("help")) {
                CommandHandler.handleCommands(input, DukeCommandType.HELP, tasks);
            } else if (input.equals("list")) {
                CommandHandler.handleCommands(input, DukeCommandType.LIST, tasks);
            } else if (input.startsWith("todo")) {
                CommandHandler.handleCommands(input, DukeCommandType.TODO, tasks);
            } else if (input.startsWith("deadline")) {
                CommandHandler.handleCommands(input, DukeCommandType.DEADLINE, tasks);
            } else if (input.startsWith("event")) {
                CommandHandler.handleCommands(input, DukeCommandType.EVENT, tasks);
            } else if (input.startsWith("done")) {
                CommandHandler.handleCommands(input, DukeCommandType.DONE, tasks);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                CommandHandler.handleCommands(input, DukeCommandType.DELETE, tasks);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("find")) {
                CommandHandler.handleCommands(input, DukeCommandType.FIND, tasks);
            } else {
                CommandHandler.handleCommands(input, DukeCommandType.UNKNOWN, tasks);
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
    }
}