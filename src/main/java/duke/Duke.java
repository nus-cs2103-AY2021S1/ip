package duke;

import java.io.IOException;

/**
 * Main class that runs the Duke program
 */
public class Duke {

    private Storage storage;
    private static TaskList tasks;
    private Ui ui;

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
     * Getter to retrieve tasks
     *
     * @return list of tasks
     */
    public static TaskList getTasks() {
        return tasks;
    }

    /**
     * Main function that runs the program
     * If the exit command is not given, the program will continue to read user inputs
     * Otherwise the storage is cleared and updated with the existing tasks
     *
     * @throws IOException if storage is not found
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Parser.parse(fullCommand);
            isExit = Parser.getExitStatus();
            if (!isExit) {
                ui.showLine();
            }
        }
        storage.clear();
        storage.save(tasks);
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
