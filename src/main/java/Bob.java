import main.java.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Bob {
    private TaskList tasks = new TaskList();
    private Storage storage;
    private UI ui;

    public Bob(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.initialiseStorage();
            storage.loadSave(tasks);
        } catch (BobException e) {
            ui.printError(e.getMessage());
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.printError(e.getMessage());
            } finally {

            }
        }
    }
    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }
}
