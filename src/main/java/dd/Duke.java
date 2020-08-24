package dd;

import dd.commands.Command;
import dd.parser.Parser;
import dd.storage.DataStorage;
import dd.tasks.TaskList;
import dd.ui.Ui;

import java.io.IOException;

public class Duke {
    private final DataStorage ds;
    private final Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ds = new DataStorage();
        this.ui = new Ui();
        try {
            tasks = new TaskList(ds.loadData());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greeting();

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readInput();
            Command c = Parser.parse(input);

            if (c != null) {
                c.execute(tasks, ui, ds);
                isExit = c.isExit();
            }
            else {
                // not valid command
                System.out.println("Sorry what?");
            }
            ui.printLine();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
