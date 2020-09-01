package duke;

import duke.command.Command;
import duke.command.DukeRunTimeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * The class Duke denotes the faithful robot.
 *
 * @author Alvin Chee
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructs a Duke robot.
     *
     * @param filePath  FilePath to store the data file.
     */
    Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        storage.addDirIfRequired();
        tasks = new TaskList(storage.load());
    }

    /**
     * Bot introduces and gets input from user.
     */
    public void run() {
        ui.showIntro();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);
            isExit = c.isExit();
        }
    }

    /**
     * Executes all the operations stated.
     *
     * @param input  String arrays of operations.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Executes all the operations stated.
     *
     * @param args  String arrays of operations.
     */
    public static void main(String[] args) throws DukeRunTimeException {
        //new Duke(getFilePath()).run();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }
}
