package duke.main;

/**
 * Class representing the Duke chat-bot.
 * Duke primarily keeps tracks of tasks, and supports various commands on the task list.
 * It saves tasks to data/tasks.txt and reads the list upon being initialised.
 */
public class Duke {
    public static final String GREETING = "Hello, I'm your chatbot!\n"
            + "What can I do for you?\n\n"
            + "GUI elements are adapted from https://se-education.org/guides/tutorials/javaFx.html";

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
