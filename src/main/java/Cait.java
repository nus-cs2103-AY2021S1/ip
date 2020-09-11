import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Main class for Cait.
 */
public class Cait {

    private static String fileName = "cait_data.txt";

    /** Storage for storing user's data */
    private Storage storage;

    /** Tasklist for dealing with the user's data */
    private TaskList tasks;

    /** Ui to deal with interactions with user */
    private Ui ui;

    /** Parser for parsing user's inputs */
    private Parser parser;

    /**
     * Constructs a new Cait object.
     */
    public Cait() {
        storage = new Storage(fileName);
        tasks = new TaskList(storage);
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }

    /**
     * Constructs a new Cait object.
     * @param fileName the name of the data file
     */
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
        ui.showLine();
        boolean isExit = ui.getIsExit();
        while (!isExit) {
            ui.readInput();
            isExit = ui.getIsExit();
        }
    }

    protected String getResponse(String input) {
        String result = parser.manageTask(input);
        return result;
    }

    /**
     * Creates a new Cait object and starts running.
     */
    public static void main(String[] args) {
        new Cait(fileName).run();
    }

}
