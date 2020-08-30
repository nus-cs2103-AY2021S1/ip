package duke.main;

/**
 * Duke is a chatbot that allows crud operations, and can add three different types of task,
 * namely todo, events, and deadlines
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.ui.commandParser(input, this.tasks);
    }
}
