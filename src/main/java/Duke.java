import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import command.Command;
/**
 * Driver class for Duke chat bot called "Jarvis"
 */
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandReader reader = new CommandReader();
        CommandAgent agent = new CommandAgent(new DukeBuffer());
        Command command;

        System.out.println("Hello! I'm Jarvis\n"
                + "What can I do for you?");

        do {
            String userInput = in.nextLine();
            command = reader.readCommand(userInput);
            String result = agent.handleCommand(command);
            System.out.println(result);
        } while (!command.isExit());

        System.out.println(agent.handleCommand(command));
    }
}
