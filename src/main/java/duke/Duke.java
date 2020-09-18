package duke;

import duke.textstoreandprint.TextPrinter;
import javafx.application.Application;

import java.nio.file.Path;

public class Duke {
    //comment change

    private TaskList taskList;
    private Path pathToSave;
    private Ui ui;

    private Duke(Path path) {
//        pathToSave = path;
//        taskList = FileManager.readFromSave(path);
//        ui = new Ui(taskList);
    }

    private void run() {

        FileManager.saveList(taskList, pathToSave);

        TextPrinter.printEndMessage();
    }

    public static void main(String[] args) {
        Application.launch(WindowDisplay.class);
        TextPrinter.printStartMessage();

        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "ipSave.txt");
        new Duke(path).run();
    }
}
