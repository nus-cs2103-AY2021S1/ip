import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.File;
import java.io.IOException;

/**
 * Duke is a application that helps you keep track of your ToDos, Deadlines and Events all in one list.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises taskList and storage of Duke
     */
    public Duke() {
        String filePath = "./data";
        String fileName = "data.txt";
        this.storage = new Storage(filePath + "/" + fileName);
        try {
            File dir = new File(filePath);
            File file = new File(filePath, fileName);
            if (dir.exists() && file.exists()) {
                this.taskList = new TaskList(storage.load());
            } else if (dir.exists()) {
                createFile();
            } else {
                createDirectory(dir);
                createFile();
            }
        } catch (DukeException e) {
            Ui.printException(e);
            System.exit(1);
        }
    }

    private void createDirectory(File dir) {
        dir.mkdir();
    }

    private void createFile() throws DukeException {
        try {
            storage.createFile();
            this.taskList = new TaskList();
        } catch (IOException e) {
            throw new DukeException("Error Creating File");
        }
    }

    /**
     * Complements the implementation of JavaFX.
     *
     * @param input User input.
     * @return A string.
     */
    String getResponse(String input) {
        Command command;
        try {
            if (Parser.isBye(input)) {
                this.storage.save(this.taskList);
                System.exit(0);
            }
            command = Parser.parseInput(input, taskList);
            return command.execute();
        } catch (DukeException e) {
            return Ui.printException(e);
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
