import java.util.Scanner;
import java.io.IOException;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Represents an interactive chat bot named "Duke"
 * who can manage simple tasks for users.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke chat bot.
     * @param filePath the relative path of assigned
     * file for reading and writing of data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        File file = new File(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method simulate the interaction between
     * Duke and human users.
     * @param args Unused.
     * @return Nothing.
     */
    public void run() {
        ui.greet();
        while (ui.getIn().hasNextLine()) {
            String command = ui.getUserCommand();
            Parser.respond(command, ui, tasks, storage.filePath);
        }
    }

    /**
     * This is the main method which makes use of run method.
     * A new Duke chat bot will be initiated by reading from and writing
     * into the file of relative path "data/tasks.txt".
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

