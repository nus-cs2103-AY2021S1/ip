package duke;

import duke.command.*;
import duke.exception.*;
import duke.parser.*;
import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks =  new TaskList(storage.load());
    }

    public void run() {
        ui.sendGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}