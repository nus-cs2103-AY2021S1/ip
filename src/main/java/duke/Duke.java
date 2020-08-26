package duke;

import ui.Ui;
import storage.Storage;
import taskList.TaskList;
import parser.Parser;

/**
 * Initializes the Java Duke Program, loads the task list
 * from the hard disk and gets the UI running.
 *
 * @author (Sruthi)
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Initializes instances of TaskList, Storage, Parser and UI
     * and gets the task list from the storage.
     *
     * @param filePath path of the file from the root
     */
    Duke(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath, taskList);
        parser = new Parser(taskList, storage);
        ui = new Ui(parser);
        try {
            storage.getTodoList();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Starts the user interaction with the Java Duke Program by running the UI
     */
    public void run() {
        ui.run();
    }

    /**
     * Prints the logo of the Java Duke Program and initializes an instance of Duke
     * with the filepath containing the task list before running the Duke Program
     * @param args args from the terminal
     */
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Duke duke = new Duke("src/data/duke.txt");
        duke.run();
    }
}
