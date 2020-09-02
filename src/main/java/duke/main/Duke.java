package duke.main;

/**
 * Class representing the Duke chat-bot.
 * Duke primarily keeps tracks of tasks, and supports various commands on the task list.
 * It saves tasks to data/tasks.txt and reads the list upon being initialised.
 */
public class Duke {
    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Duke object that reads data from the default file path.
     * The file path is taken to be data/tasks.txt
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.taskList = storage.getTasksFromFile();
        this.parser = new Parser(taskList);
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.writeToFile(taskList);
            return "All changes saved!";
        }
        return parser.parse(input);
    }

    /*
    private void run() {
        ui.run();
        storage.write(taskList);
    }
     */
}
