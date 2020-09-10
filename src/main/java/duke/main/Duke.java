package duke.main;

import duke.Ui.Main;
import duke.exception.StorageException;
import javafx.application.Application;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Duke is a chatbot that can help us manage and store our various kinds of Task.
 */
public class Duke {
    /** Boolean to check if the system should terminate or not. **/
    private boolean shouldExit;
    /** TaskList to store Tasks. */
    private TaskList tasks;
    /** Storage to store data to hard disk */
    private final Storage storage;

    /**
     * Constructs a Duke.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        // Add data from file from hard disk.
        try {
            tasks = storage.readFromHardDisk();
        } catch (StorageException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        // Launch GUI.
        Application.launch(Main.class, "");
    }

    public boolean getStatus() {
        return shouldExit;
    }

    /**
     * Gets response from the command based on the user input.
     *
     * @param input The user input.
     * @return Reply from the command as a String to be displayed in the dialog box.
     */
    public String getResponse(String[] input) {
        try {
            Command command = Parser.parse(input);
            command.perform(tasks);
            storage.writeToHardDisk(tasks);
            shouldExit = command.isExit();
            return command.getReply();
        } catch (DukeException | IOException e) {
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

