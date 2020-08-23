package duke;

import duke.command.Command;
import duke.command.InvalidCommandException;
import duke.component.*;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new ActualStorage(filePath);
            list = storage.getList();
        } catch (Exception e) {
            ui.output(e.getMessage());
            list = new TaskList();
        }
    }

    public void run() {
        boolean flag = true;
        ui.greeting();
        while (flag) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(ui, list, storage);
                flag = !c.isExit();
            } catch (InvalidCommandException e) {
                ui.output(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        String path = "../data/tasks.txt";
        new Duke(path).run();
    }
}
