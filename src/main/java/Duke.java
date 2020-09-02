import duke.backend.Storage;
import duke.frontend.Parser;
import duke.frontend.Ui;
import duke.task.TaskList;

import java.io.FileNotFoundException;

/**
 * Runs the main method that in turn instantiates and runs the Duke object.
 *
 * The program is a CLI Application that allows users to:
 * <ul>
 *     <li>Add new tasks (Deadline, Event or Todo)</li>
 *     <li>View a list of their tasks</li>
 *     <li>Mark tasks as done</li>
 *     <li>Delete tasks</li>
 *     <li>Save tasks in the hard disk automatically</li>
 * </ul>
 *
 * @author Damith C. Rajapakse
 * @author Jeffry Lum
 * @author Jiachen
 * @author Jeanne Toh
 */
public class Duke {
    private Storage storage;

    private TaskList taskList;
    private Parser parser;

    private Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
            parser = new Parser(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not created. " + e);
        }
    }

    private void run() {
        Ui.greet();
        parser.parseInputCommands();
        storage.save(taskList);
        Ui.exit();
    }

    /**
     * Creates a new instance of Duke that saves tasks to filePath "./data/saved-tasks.txt" and runs it.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        new Duke("./data/saved-tasks.txt").run();
    }
}
