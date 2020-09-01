package duke;

import duke.commands.Command;

import javax.swing.*;

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

    public String getSavedFile() {
        String savedFiles = storage.readFile();
        return savedFiles;
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // return response here
        String response;
        try {
//            String fullCommand = ui.readCommand();
//            ui.showLine();
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
//            isExit = c.isExit();
        } catch (DukeException e) {
//            ui.showError(e.getMessage());
            response = e.getMessage();
        } finally {
//            ui.showLine();
//            response = response + "reached finally block";
        }
        return response;
    }
}
