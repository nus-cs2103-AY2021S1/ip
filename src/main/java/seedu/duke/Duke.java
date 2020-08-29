package main.java.seedu.duke;

import main.java.seedu.duke.commands.Command;

/**
 * Represents the main Duke who acts as the user's personal assistant.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(this.tasks);
        this.ui = new Ui();
    }

    /**
     * Run Duke.
     */
    public void run() {
        ui.greet();
        storage.readFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    /**
     * Entry point of the whole program.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
