package duke;

/**
 * Duke class that creates a Duke bot to interact with user.
 */

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final String filePath;

    /**
     * Constructs a duke that read a file from filePath to retrieve previously stored data
     * @param filePath the file path of the stored data
     */

    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method to get duke to start running and ask for user input
     */

    public void run() {
        //...
        Parser p = new Parser();
        boolean isExit = false;
        while(!isExit) {
            String command = ui.ask();
            if (command.contains("bye")) {
                isExit = true;
            } else {
                p.parse(command);
            }
        }
        storage.updateDatabase(tasks.getTaskList(), filePath);
        ui.bye();
    }
    public static void main(String[] args) {
        new Duke("data").run();
    }
}

