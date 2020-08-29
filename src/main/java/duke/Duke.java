package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initialises a new instance of Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Entry point of the program.
     *
     * @param args This parameter is not used.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        this.ui.print("Hello! I'm Duke\nWhat can I do for you?");

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns the message that Duke will use to greet the user when first launching the
     * application.
     *
     * @return The greeting message.
     */
    public String greetUser() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
