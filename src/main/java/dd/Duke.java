package dd;

import dd.commands.Command;
import dd.exception.DukeException;
import dd.parser.Parser;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

import java.io.IOException;

public class Duke {

    private DataStorage ds;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ds = new DataStorage();
        this.ui = new Ui();

        try {
            tasks = new TaskList(ds.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greeting();

        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);

                c.execute(tasks, ui, ds);
                isExit = c.isExit();
            } catch (DukeException e){
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
