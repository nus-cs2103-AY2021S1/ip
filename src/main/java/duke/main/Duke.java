package duke.main;

import duke.Ui.Main;
import javafx.application.Application;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke is a chatbot that can help us manage and store our various kinds of Task.
 */
public class Duke {

    /** TaskList to store Tasks. */
    private final TaskList tasks;
    /** Storage to store data to hard disk */
    private final Storage storage;

    /**
     * Constructs a Duke.
     */
    public Duke() {
        // Create storage
        storage = new Storage();

        // Read from hard disk
        tasks = storage.readFromHardDisk();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        // Launch GUI
        Application.launch(Main.class, "");
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.perform(tasks);
            storage.writeToHardDisk(tasks);
            return command.getReply();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method of duke.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
