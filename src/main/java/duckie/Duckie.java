package duckie;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import duckie.command.Command;
import duckie.exception.DuckieException;
import duckie.task.TaskList;

/**
 * Main file for the chatbot Duckie
 */
public class Duckie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiate the Duckie object together with the filePath of duckie file
     * @param filePath Duckie file location
     */
    public Duckie(String filePath) {
        ui = new Ui();
        ui.showIntro();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DuckieException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Activates the chatbot to take in commands
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DuckieException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method activating run() method
     * @param args
     */
    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        Path dirPath = Paths.get(cwd, "data");
        Path filePath = Paths.get(cwd, "data", "duckie.txt");
        new Duckie(String.valueOf(filePath)).run();
    }
}
