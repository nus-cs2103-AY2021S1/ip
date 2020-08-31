package duke;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;
    private final Parser parser;

    /**
     * Creates new UI, storage and parser objects.
     * Retrieves the data from storage indicated by the filePath
     * Creates a new TaskList if data could not be retrieved
     * @param filePath filePath where the data for tasks is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Reads input from the user and carries out the commands until command "bye" is entered
     */
    public void run() {
        ui.showWelcomeMessage();
        String next = ui.readUserInput();
        while (!next.equalsIgnoreCase("Bye")) {
            parser.sortInput(next, taskList, storage, ui);
            next = ui.readUserInput();
        }
        ui.showByeMessage();
    }


    /**
     * Main entry point of the program
     */
    public static void main(String[] args) {
        String path = "data/data.txt";
        new Duke(path).run();
    }
}

