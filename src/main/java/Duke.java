import java.util.Scanner;

import command.Command;
/**
 * Driver class for Duke chat bot called "Jarvis"
 */
public class Duke {

    /**
     * Executes the "Jarvis" bot to run.
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
        } while (!command.isExit());
    }
}
