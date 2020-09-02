package duke.main;

/**
 * Class representing the Duke chat-bot.
 * Duke primarily keeps tracks of tasks, and supports various commands on the task list.
 * It saves tasks to data/tasks.txt and reads the list upon being initialised.
 */
public class Duke {
    private final Parser parser;

    /**
     * Constructs a Duke object that reads data from the default file path.
     * The file path is taken to be data/tasks.txt
     */
    public Duke() {
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList = storage.getTasksFromFile();
        this.parser = new Parser(storage, taskList);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

    /*
    private void run() {
        ui.run();
        storage.write(taskList);
    }
     */
}
