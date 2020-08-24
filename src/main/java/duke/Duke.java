package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private final Parser parser;
    private final Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("duke.json");
            this.taskList = this.storage.load();
        } catch (DukeException e) {
            ui.systemMessage(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            this.parser = new Parser(this.taskList, this.ui);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        ui.welcomeMessage();
        while (ui.isActive()) {
            String input = ui.nextLine();
            try {
                parser.parseAndRun(input);
                this.storage.save(this.taskList);
            } catch (DukeException e) {
                ui.systemMessage(e.getMessage());
            }
        }
    }

}
