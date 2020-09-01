package duke;

import duke.command.Command;

/**
 * Represents a personalized chat bot where a user can keep track of different tasks.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke chat bot that loads and saves tasks in the filepath.
     * @param filePath is the path in which tasks are loaded and saved in.
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
     * Executes the chat bot and represents the main driver of the program.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Acts as the starting mechanism to run the chat bot.
     * @param args accepts any parameter.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}