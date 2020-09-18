package duke;

import duke.TextStoreAndPrint.TextPrinter;

import java.nio.file.Path;

public class Duke {
    // comment

    private Ui ui;
    private TaskList taskList;
    private Path pathToSave;

    private Duke(Path path) {
        pathToSave = path;
        taskList = FileManager.readFromSave(path);
        ui = new Ui(taskList);
    }

    private void run() {
        ui.run();
        FileManager.saveList(taskList, pathToSave);
        TextPrinter.printEndMessage();
    }

    public static void main(String[] args) {

        TextPrinter.printStartMessage();

        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "ipSave.txt");
        new Duke(path).run();
    }
}
