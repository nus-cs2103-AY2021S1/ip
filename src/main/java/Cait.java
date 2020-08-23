import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Main class for Cait.
 */
public class Cait {

    /** Storage for storing user's data */
    private Storage storage;

    /** Tasklist for dealing with the user's data */
    private TaskList tasks;

    /** Ui to deal with interactions with user */
    private Ui ui;

    /** Parser for parsing user's inputs */
    private Parser parser;

    public Cait(String fileName) {
        storage = new Storage(fileName);
        tasks = new TaskList(storage);
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }

    /**
     * Starts running Cait until the user exits.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = ui.isExit();
        while (!isExit) {
            ui.readInput();
            isExit = ui.isExit();
        }
    }

    /**
     * Creates a new Cait object and starts running.
     */
    public static void main(String[] args) {
        new Cait("duke_data.txt").run();
    }
}
