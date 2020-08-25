package core;

import command.Command;
import command.CommandHandler;

import java.io.IOException;
import java.util.stream.Stream;

public class IPbot {

    /**
     * Entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Storage.load();
        } catch (IOException e) {
            Ui.print(e.getMessage());
        }

        Ui.print("Hello from iPbot, what can I do for you?");

        if (Ui.missingInput()) {
            Ui.print("No input found. Exiting!");
            return;
        }

        Stream.generate(Ui::getCommand)
            .takeWhile(input -> !Command.EXIT_CMD.getCmdString().equals(input))
            .forEach(new CommandHandler(true));

        Ui.print("Goodbye!");
    }



}
