package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.commands.Command;
import duke.exceptions.DukeException;
import javafx.application.Platform;

/**
 * Class that initiates the Duke object. Contains methods to run the duke bot.
 * the appropriate Command object.
 */
public class Duke {

    // path of the data to be stored
    private static final Path path = Paths.get(".", "data", "duke.txt");

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Initializes Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
            tasks = new TaskList(storage.getCurrentTasks());
        } catch (DukeException e) {
            ui.errorMessage(e.getMessage());
        }
    }

    public String getGreetingMessage() {
        return ui.welcomeMessage(tasks.toString());
    }


    /**
     * Sets a timeout for the application.
     * @author Shilo
     * Adapted https://gist.github.com/Shilo/207c7ba4a604b7811b77ff17be8580f3
     *
     * @param runnable Function to be executed after the timeout.
     * @param delay Delay in ms.
     */
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    /**
     * Gets the expected response of the bot given the input command by the user.
     * Controls the interaction between different classes to
     * achieve the bot behavior. Bot stops when "bye" command is entered and saving the current
     * task list in the process.
     *
     * @param input Command of the user.
     * @return String response from the bot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.executeCommand(tasks, ui, storage);
            if (c.isExit()) {
                storage.saveFile(tasks.getTaskList());
                setTimeout(()-> {
                    Platform.exit();
                    System.exit(0);
                }, 1000);
            }
            return output;
        } catch (DukeException e) {
            return ui.errorMessage(e.getMessage());
        }
    }

}
