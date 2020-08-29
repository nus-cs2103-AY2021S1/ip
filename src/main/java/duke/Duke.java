package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.scene.control.Label;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Duke is a text-based bot that tracks different types of tasks.
 * Supports the creation of Todo, Deadline & Event Tasks
 * as well as other commands such as search, marking as done etc.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String DEFAULT_SAVE_FILE = ".\\data\\duke.txt";

    /**
     * Constructs a Duke object and initialises Ui, Storage & TaskList classes.
     * @param filePath Path of local save file for Duke's task list.
     */
    private Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui.greetingMessage();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Public, no-args constructor for JavaFX use only.
     */
    public Duke() {
        this (DEFAULT_SAVE_FILE);
    }

    /**
     * Starts running process of Duke.
     * While running, reads user input through Ui,
     * Parser determines the associated Command given,
     * The Command is executed and TaskList is saved to drive.
     * This method also encapsulates each reply from Duke with a
     * line separator above and below.
     */
    public void run() {
        boolean running = true;
        while (running) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.store(tasks);
                if (c.isExit()) {
                    running = false;
                }
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main function of Duke
     * Checks if the save folder for Duke exists before creating a new Duke Object.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Function to get String response from Duke.
     * @param input String input from user.
     */
    public String getResponse(String input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.store(tasks);

            // Put things back
            System.out.flush();
            System.setOut(old);

            return c.isExit() ? "Close the window to stop me." : baos.toString();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
