package sparkles;

import sparkles.command.Command;
import sparkles.task.TaskList;
import sparkles.util.Parser;
import sparkles.util.Storage;
import sparkles.util.Ui;

public class Sparkles {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Sparkles(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SparklesException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SparklesException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Sparkles("data/tasks.txt").run();
    }
}
