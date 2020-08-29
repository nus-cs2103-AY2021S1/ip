import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        parser = new Parser(tasks);
        storage = new Storage(filePath);
        try {
            storage.loadTasks(tasks);
        } catch (IOException e) {
            ui.printLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        while(!parser.isFinished()) {
            try {
                parser.parse(ui.read());
            } catch(DukeException e) {
                ui.printException(e);
            }
        }

        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            ui.printSavingError();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}
