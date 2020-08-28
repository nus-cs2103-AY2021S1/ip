package seedu.duke;

import seedu.duke.command.Command;

/**
 * Represents a chatbot that takes in and executes commands from the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filepath directory and name of the file to save the user's tasks to
     */
    Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.readTasks();
            this.ui = new Ui();
        } catch (DukeException e) {
            // System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the chatbot so that it takes in commands from the user.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(this.ui.readCommand());
                if (command != null) {
                    command.execute(this.taskList, this.ui, this.storage);
                    isExit = command.isDone();
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String filepath = "/data/taskList.txt";
        Duke duke = new Duke(filepath);
        duke.run();
    }
}
