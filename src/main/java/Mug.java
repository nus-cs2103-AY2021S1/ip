import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * A tool to save task.
 */
public class Mug {
    /** user Interface Object*/
    private final Ui ui;
    /** A list of Tasks */
    private final TaskList tasks;
    /**
     * Constructs a Mug Object that create/read the file from the given filepath
     * and pass the information from the file to TaskList.
     */
    public Mug() {
        this.ui = new Ui();
        Storage storage = new Storage("mug.txt");
        this.tasks = new TaskList(storage);
    }

    /**
     * Returns respond from mug to user.
     * @param input User's input
     * @return Mug's responds.
     */
    public String getResponse(String input) {
        new Mug();
        return this.ui.readCommand(input, this.tasks);
    }
}
