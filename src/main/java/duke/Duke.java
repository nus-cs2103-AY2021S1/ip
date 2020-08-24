package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static TaskList getTasks() {
        return tasks;
    }

    /**
     * Takes in user inputs. Program terminates when the String "bye" is entered.
     * Program stores user inputs as Tasks and returns the list when the String "list" is entered.
     * Tasks are categorised into "todo", "deadline" (to specify "by") and "event"  (to specify "at").
     * When "done xx" is entered, Task xx in the list is marked as done.
     * When "delete xx" is entered, Task xx in the list is removed from the list.
     */

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Parser.parse(fullCommand);
            isExit = Parser.isExit;
            if (!isExit) {
                ui.showLine();
            }
        }
        storage.clear();
        storage.save(tasks);
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\Cedric\\Desktop\\Repo\\iP\\src\\main\\java\\data\\duke.txt").run();
    }
}
