package core;

import command.Command;
import command.CommandHandler;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Main class containing the entry point of the program.
 */
public class IPbot {

    // task list
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
