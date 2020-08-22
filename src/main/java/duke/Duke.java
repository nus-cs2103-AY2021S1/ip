package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tool.Parser;
import duke.tool.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printLog(e.getMessage());
        }
    }

    public void run() {
        ui.showLogo();
        ui.showWelcomeMessage();
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.excute(tasks,ui,storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printLog(e.getMessage());
            }
        }
    }

    public static void main(String[] args)  {
        new Duke("data/duke.txt").run();
    }
}
