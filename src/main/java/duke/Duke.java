package duke;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

import commands.Command;
import exceptions.DukeException;
import tasks.TaskList;

/**
 * Over-arching class containing the main information of the Duke bot.
 */

public class Duke {
    private TaskList tasks;
    private boolean isQuit;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Attempts to read an existing stored data.txt file, and creates a new data.txt file if one is not found.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.isQuit = false;
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(Paths.get("data.txt").toFile(), tasks);
            storage.readData();
        } catch (FileNotFoundException e) {
            System.out.println("No data found, creating new .txt file");
            this.storage = new Storage();
        }
    }

    public TaskList getTaskList() {
        return tasks;
    }
    public Storage getStorage() {
        return storage;
    }
    public Ui getUi() {
        return ui;
    }
    public Parser getParser() {
        return parser;
    }

    /**
     * Allows the system to begin taking in user input and edits the stored data accordingly. Runs until an "exit"
     * command is received.
     */

    public void run() {
        while (!isQuit) {
            String input = ui.takeInput();
            try {
                Command command = parser.parse(input);
                command.exec(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printException(e);
            }
            isQuit = parser.getQuit();
        }
    }

    public String getResponse() {
        return ui.getMessage();
    }

    /**
     * Driver method.
     * @param args args.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
