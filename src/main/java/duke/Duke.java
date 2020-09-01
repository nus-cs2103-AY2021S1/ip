package duke;

import duke.command.Command;
import duke.command.UnknownCommand;
import duke.exception.DukeException;
import duke.exception.WrongFormatException;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * Represents the Duke chat bot. Duke can keep a record of user's inputs as a list of tasks, mark them as completed
 * when they are done, and show the user the list of tasks upon request. This list of tasks will be written to the
 * user's hard disk such that it can be saved and loaded. Currently, Duke supports 3 types of tasks: to-do, deadline
 * and event tasks.
 */
public class Duke {

    /** Deals with loading tasks from and saving tasks to the save file on the hard disk */
    private Storage storage;

    /** The user's list of tasks */
    private TaskList tasks;

    /** Deals with interactions with the user for CLI version of Duke */
    private Ui ui;

    /** Deals with interactions with the user for GUI version of Duke */
    private UiForGui uiForGui;

    /** The relative path in which the save file is located */
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Creates and initializes the Duke program.
     *
     * @param filePath The relative path in which the save file is located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | WrongFormatException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public Duke() {
        uiForGui = new UiForGui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | WrongFormatException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke program by calling the run() method.
     *
     * @param args User input arguments (not used).
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    /**
     * Executes the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) { // Duke takes in user input indefinitely until the user says "bye"
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit) {
                Thread newThread = new Thread(() -> exitProgram());
                newThread.start();
            }
            return new Response(c.execute(tasks, uiForGui, storage), c);
        } catch (DukeException e) {
            return new Response(uiForGui.showError(e), new UnknownCommand());
        }
    }

    public void exitProgram() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}
