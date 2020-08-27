package duke;

import duke.command.Command;

/**
 * The main program
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList taskList;

        try {
            taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
            taskList = new TaskList();
        }

        this.taskList = taskList;
    }

    /**
     * Runs the main program.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
