package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

public class DukeController {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public DukeController() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.getTasks());
    }

    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }
}
