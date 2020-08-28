import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String pathFile) {
        this.ui = new Ui();
        this.storage = new Storage(pathFile);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // todo
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
