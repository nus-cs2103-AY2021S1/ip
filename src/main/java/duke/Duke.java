package duke;

import commands.Command;
import exceptions.DukeException;
import tasks.TaskList;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Over-arching class containing the main information of the Duke bot.
 */

class Duke {
    private TaskList tasks;
    private boolean quit;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Attempts to read an existing stored data.txt file, and creates a new data.txt file if one is not found
     */
    private Duke() {
        this.tasks = new TaskList();
        this.quit = false;
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(Paths.get("data.txt").toFile(), tasks);
            storage.readData();
        } catch (FileNotFoundException e){
            System.out.println("No data found, creating new .txt file");
            this.storage = new Storage();
        }
    }

    /**
     * Allows the system to begin taking in user input and edits the stored data accordingly. Runs until an "exit"
     * command is received.
     */

    private void run() {
        ui.showWelcome();
        while (!quit) {
            String input = ui.takeInput();
            try {
                Command command = parser.parse(input);
                command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printException(e);
            }
            quit = parser.isQuit();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
