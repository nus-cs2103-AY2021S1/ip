package duke;

import duke.command.Command;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Duke Chatbot that saves and loads tasks from the given filepath.
     * @param filePath The file path to load tasks from and save tasks to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Chatbot until an exit command is issued.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Activates the Chatbot to run.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
