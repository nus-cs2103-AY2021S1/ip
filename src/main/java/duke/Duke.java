package duke;

import duke.command.UserCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();

    }

    /**
     * Executes the Duke program.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (isExit != true) {
            try {
                String input = ui.readCommand();
                UserCommand command = Parser.parse(input);
                isExit = command.isExit;
                command.execute(tasks, ui);
                storage.save(tasks);
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./taskdata.txt").run();
    }

}



