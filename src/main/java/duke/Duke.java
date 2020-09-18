package duke;

import duke.command.Command;

/**
 * Creates a Duke object that represents a bot that manages a user's tasks.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    
    /**
     * Creates a duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(this.tasks, this.ui, this.storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    
    /**
     * Creates a Duke object and runs the program.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
