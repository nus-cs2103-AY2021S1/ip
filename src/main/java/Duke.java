import static ui.Ui.echo;
import static ui.Ui.welcome;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;

public class Duke {
    private Storage stores;
    private TaskList tasks;

    /**
     * constructor
     * @param filePath save file path
     */
    public Duke(String filePath) {
        this.stores = new Storage(filePath);
        try {
            echo("Loading started");
            this.tasks = new TaskList(stores);
            echo("Loading ended");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * prints a welcome message and scans for user input
     */
    public void run() {
        welcome();
        Parser.accept(tasks, stores);
    }

    public Storage getStores() {
        return stores;
    }

    /**
     * It's psvm, what do you want?
     * @param args Please it's just psvm
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new Duke("./data/duke.txt").run();
        } else {
            // enter full save file path for jar file
            new Duke(args[0]).run();
        }
    }

}
