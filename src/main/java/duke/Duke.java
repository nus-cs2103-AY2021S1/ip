package duke;

import java.io.IOException;

/**
 * The Duke program represents a person assistant
 * chatbot that helps the user manage tasks and store them 
 * in a list according to the user input.
 * 
 * @author York Tat
 * @version 1.0
 * @since 2020-08-14
 */
public class Duke {
    private Storage store;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new Duke chatbot that saves and loads tasks from the given filePath
     * @param filePath
     */
    private Duke(String filePath) {
        this.ui = new Ui();
        this.store = new Storage(filePath);
        try {
            this.taskList = new TaskList(store.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    
    /**
     * Runs the chatbot until an exit command is issued.
     */
    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, store);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * This is the main method that initialises Duke which makes use of the run method.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("../data/taskmanager.txt").run();
    }
}