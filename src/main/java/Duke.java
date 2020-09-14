import java.io.IOException;
import java.util.Scanner;

/**
 * Represents Duke bot and contains main information of how it works.
 */
public class Duke {
    private static final String PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Task duplicateTask;
    private Ui ui;
    private Parser parser;
    
    /**
     * Creates Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }
    
    /**
     * Creates Duke object.
     * 
     * @param filePath Pathname of the file that stores tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Reads user input then acts accordingly and stores user data.
     */
    public void run() {
        ui.printGreet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //parser.parse(sc.nextLine(), tasks, ui, storage);
        }
    }

    /**
     * Gets list of tasks.
     * 
     * @return List of tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Gets duplicate task.
     *
     * @return Duplicate task.
     */
    public Task getDuplicateTask() {
        return this.duplicateTask;
    }

    /**
     * Sets duplicate task.
     *
     */
    public void setDuplicateTask(Task task) {
        this.duplicateTask = task;
    }

    /**
     * Deletes duplicate task.
     *
     */
    public void resetDuplicateTask() {
        this.duplicateTask = null;
    }
    
    /**
     * Gets storage.
     * 
     * @return Storage of Duke.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Gets parser.
     * 
     * @return Parser of Duke.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Gets Ui.
     * 
     * @return Ui of Duke.
     */
    public Ui getUi() {
        return this.ui;
    }
    
}