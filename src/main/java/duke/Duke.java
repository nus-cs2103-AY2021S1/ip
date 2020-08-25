package duke;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.msg);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String fullCommand = ui.readCommand();

        while (true) {
            try {
                String[] commands = Parser.parse(fullCommand);
                if (commands[0].equals("bye")) break;
                tasks.runCommand(commands, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.msg);
            }
            fullCommand = ui.readCommand();
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
