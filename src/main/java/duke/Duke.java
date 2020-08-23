package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    public void run() {
        ui.printHello();
        tasks = storage.load();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(tasks, ui);
                isExit = c.isExit();
                storage.save(tasks);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
