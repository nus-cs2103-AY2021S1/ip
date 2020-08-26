import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Scanner;

/**
 * Main driving force for the Duke application.
 */
public class Duke {

<<<<<<< HEAD
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
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
        } catch (Exception e) { // Change to DukeException
            ui.showError(e.getMessage());
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}