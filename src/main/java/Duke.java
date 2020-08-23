import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private DukeStorage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Duke(String filePath) {
        storage = new DukeStorage(filePath);
        ui = new Ui();
        taskList = new TaskList();
        // try to open the duke file
        try {
            storage.reloadStorage(taskList.getTasks());
        } catch (FileNotFoundException ex) {
            System.out.println("Duke data do not exist!");
        } catch (DukeException ex) {
            System.out.println("Its a duke exception!");
        }
        parser = new Parser(storage, ui, taskList);
    }

    public void run() {
        ui.greet();
        boolean isDukeOn = true;
        while (isDukeOn) {
            String input = ui.getInput();
            String[] splittedWords = input.split("\\s", 2); // splits string based on whitespace
            String command = splittedWords[0];
            String afterCommand = null;
            if (splittedWords.length > 1) {
                afterCommand = splittedWords[1];
            }

            try {
                parser.askDuke(command, afterCommand);
                isDukeOn = parser.isDukeOn();
            } catch (DukeException ex) {
                ui.format(ex.toString());
            }
        }
        ui.getScanner().close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
