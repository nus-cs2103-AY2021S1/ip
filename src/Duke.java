
import java.io.IOException;

import Command.Command;
import main.java.*;
import Exception.*;

/**
 * A chat bot to save the todo, deadline and event task in a list.
 */
public class Duke{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

    /**
     * A function which takes user input and never stops unless bye is called.
     */
    public void run() {
        this.ui.hello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
                ui.showSpace();
            }
        }

    }

}
