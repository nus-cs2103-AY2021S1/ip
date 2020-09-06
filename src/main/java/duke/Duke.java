package duke;

import java.io.IOException;

/**
 * Main class that runs the Duke program
 */
public class Duke {

    private static TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for creating Duke object
     *
     * @param filePath relative directory of the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input
     * @param input user input
     * @return duke's reply
     * @throws IOException if filePath does not exist
     */
    public String getResponse(String input) throws IOException {
        Parser.setTasks(tasks);
        Parser.setStorage(storage);
        return Parser.parse(input);
    }

    /**
     * Getter to retrieve tasks
     *
     * @return list of tasks
     */
    public static TaskList getTasks() {
        return tasks;
    }
}
