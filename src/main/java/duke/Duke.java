package duke;

import duke.commands.Command;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Bot with personality which assists and keeps track of user's tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor to create Duke object.
     *
     * <p>
     * Contains a <code>Ui</code>, a <code>Storage</code>,
     * \a <code>Parser</code> and a <code>TaskList</code>
     * which is responsible for user interaction,
     * saving and writing user's tasks to user's local storage and
     * making sense of user's commands and keeping track of user's tasks respectively.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Responsible for receiving user's input and executing program.
     *
     * <p>
     * Communicates with user via Ui, sends user input to parser
     * to parse and find command of user, executes command and
     * sends result to be saved in storage.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.findCommand(fullCommand);
                command.execute(tasks, ui, storage);
                storage.save(tasks.getList());
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts Duke up.
     *
     * @param args user input from the command line.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}


