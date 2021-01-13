import static ui.Ui.echo;
import static ui.Ui.welcome;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;

public class Duke {
    private Storage dukeStores;
    private TaskList dukeTasks;

    /**
     * default constructor for launcher
     */
    public Duke() {
        this.dukeStores = new Storage("./data/duke.txt");
        try {
            this.dukeTasks = new TaskList(dukeStores);
        } catch (Exception e) {
            this.dukeTasks = new TaskList();
        }
    }

    /**
     * constructor
     * @param filePath save file path
     */
    public Duke(String filePath) {
        this.dukeStores = new Storage(filePath);
        try {
            echo("Loading started");
            this.dukeTasks = new TaskList(dukeStores);
            echo("Loading ended");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.dukeTasks = new TaskList();
        }
    }

    /**
     * prints a welcome message and scans for user input
     */
    public void run() {
        welcome();
        Parser.accept(dukeTasks, dukeStores);
    }

    public Storage getDukeStores() {
        return dukeStores;
    }

    /**
     * Launches duke and tells it where save file is located
     * @param args possible full save file path
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new Duke("./data/duke.txt").run();
        } else {
            // enter full save file path for jar file
            new Duke(args[0]).run();
        }
    }

    String getResponse(String input) {
        return Parser.response(dukeTasks, dukeStores, input);
    }
}
