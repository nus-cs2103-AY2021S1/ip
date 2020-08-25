import java.io.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String command = ui.readCommand();
                isExit = Parser.parse(command, tasks, ui, storage);
            } catch (YooException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}