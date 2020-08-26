package moco.main;

import moco.logic.MocoException;
import moco.logic.Parser;
import moco.logic.Storage;
import moco.logic.TaskList;
import moco.ui.Ui;

public class Moco {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Moco(String filePath) throws MocoException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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
        new Moco("tasklist.txt").run();
    }
}