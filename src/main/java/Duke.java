package main.java;

/**
 * Duke chatbot :)
 *
 * @author Lio
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.read());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.hi();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            storage.write(tasks.toData());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        ui.bye();
    }

    /**
     * Initialises and runs the Duke chatbot.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}