import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(storage, ui, tasks);
    }
    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.getCommand();
            try {
                if (command.equals("bye")) {
                    break;
                } else {
                    parser.parse(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

    }

}
