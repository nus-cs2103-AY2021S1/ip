package duke;

import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private final Parser parser;
    private final Ui ui;
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

    public void run() {
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
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
