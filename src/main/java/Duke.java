import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Scanner;

//make some changes

/**
 * Main driving force for the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        ui.greet();
        try {
            boolean isExit = false;
            Scanner sc = new Scanner(System.in);
            while (!isExit) {
                ui.showLine();
                String nextLine = sc.nextLine();
                isExit = parser.parse(tasks, nextLine);
            }
            sc.close();
        } catch (Exception e) {
            // Change to DukeException
            ui.showError(e.getMessage());
        }
        storage.save();
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}