package duke.main;

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
            storage.write(taskList);
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
