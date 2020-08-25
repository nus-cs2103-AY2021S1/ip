package duke;

import duke.command.Command;
import duke.exception.*;

import java.io.File;

/**
 * Represents the chat bot.
 * It is the Main class.
 */
public class Duke {
    private TaskList list;
    private final Storage storage;
    private final Ui ui;

    /**
     * Class constructor.
     * @param filePath The file path of the Storage text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeInvalidDataException e) {
            ui.showLoadingError();
            list = new TaskList();
        } catch (DukeInvalidDateTimeInputException e) {
            ui.showError(e);
            list = new TaskList();
        }
    }

    /**
     * Runs the chat bot, continuously interact with user.
     * It also executes the command corresponding to user input.
     */
    public void run() {
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                isExit = !c.execute(list, ui, storage);
            } catch (DukeEmptyIndexException | DukeEmptyDescriptionException
                    | DukeEmptyByException | DukeEmptyAtException
                    | DukeInvalidDateTimeInputException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Checks to see if directory is found.
     * Creates the directory if directory is not found.
     *
     * @param filePath The file path of the directory.
     */
    public static void checkAndMakeDir(String filePath) {
        File f = new File(filePath);
        if (f.mkdir()) {
           System.out.printf("Created a directory '%s'%n", filePath);
        }
    }

    /**
     * Main method that runs the program.
     *
     * @param args The String array.
     */
    public static void main(String[] args) {
        String homePath = System.getProperty("user.home");
        checkAndMakeDir(homePath + "/data");
        Duke duke = new Duke(homePath + "/data/duke.txt");
        duke.run();
    }
}
