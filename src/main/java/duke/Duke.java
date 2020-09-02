package duke;

import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws MocoException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts Moco
     * If the user inputs "bye", Moco closes, saving task list
     *
     * @throws MocoException If insufficient details are entered/wrongly entered.
     */
    public void run() {
        ui.startBot();
        boolean isCommand = true;
        while (isCommand) {
            try {
                String input = ui.getInput();
                isCommand = Parser.parse(input, tasks, ui, storage);
            } catch (MocoException e) {
                System.out.println(e.getMessage());
                ui.printBorder();
            }
        }
        ui.stopBot();
    }

    public static void main(String[] args) throws MocoException {
        new Duke("tasklist.txt").run();
    }
}