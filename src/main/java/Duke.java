import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (FileNotFoundException e) {
//            ui.showError(e.getMessage());
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
