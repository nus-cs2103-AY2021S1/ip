package duke;

import duke.command.*;
import duke.exception.*;

public class Duke {

    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printGeneralChatWindow(e.toString());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        // Initial greeting, prompt user for commands
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.printBorder(); // Print top border
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printGeneralChatWindow(e.toString());
            } finally {
                ui.printBorder(); // Print bottom border
            }
        }

        ui.printLogo();
    }

}
