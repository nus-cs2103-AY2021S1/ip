package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.task.TaskList;

import duke.ui.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the chatbot Duke.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList tasks;
    private Storage storage;
    
    Duke() {
        ui = new Ui();
        parser = new Parser();
        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "data", "tasks.txt");
        storage = Storage.setup(path);
        tasks = storage.readData();
    }

    /**
     * Exits the program.
     */
    void exit() {
        storage.update(tasks);
        ui.exit();
        System.exit(0);
    }

    /**
     * Runs the program, which takes in and executes user input
     * and exits if the user inputs the exit command or some invalid command.
     */
    public void run() {
        try {
            ui.displayWelcome();
            boolean isExit = false;
            while (!isExit) {
                String userInput = ui.readInput();
                Command c = parser.parse(userInput);
                c.execute(tasks, ui);
                isExit = c.isExit();
            }
            exit();
        } catch (DukeException e) {
            ui.displayError(e.toString());
        }
    }

    /**
     * Runs Duke.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
