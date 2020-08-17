package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;

/**
 * Driver class for duke.Duke chat bot called "Jarvis"
 */
public class Duke {

    /**
     * Executes the "Jarvis" bot to run.
     * The bot has a reader to parse user input
     * and an agent to handle the duke.command parsed from user input
     *
     * @param args main() function arguments.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandReader reader = new CommandReader();
        CommandAgent agent = new CommandAgent();
        Command command;

        System.out.println("Hello! I'm Jarvis\n"
                + "What can I do for you?\n");

        do {
            String userInput = in.nextLine();
            command = reader.readCommand(userInput);
            String result = agent.handleCommand(command);
            System.out.println(result);
        } while (!ExitCommand.isExitCommand(command));
    }
}
