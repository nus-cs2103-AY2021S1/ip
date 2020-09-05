package fei;

import fei.command.Command;
import fei.exception.FeiException;
import fei.tool.Parser;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class Fei {

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Create a Fei's chatterbox with a file path you want to store all your data in.
     *
     * @param filePath a String on file path in format: "./data/*.txt".
     */
    public Fei(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (FeiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (FeiException e) {
            return e.getMessage();
        }
    }

//    /**
//     * Run Alison Chat bot with this method.
//     */
//    public void run() {
//        ui.greeting();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.printBorder();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (AlisonException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.printBorder();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Alison("./data/tasks.txt").run();
//    }

}
