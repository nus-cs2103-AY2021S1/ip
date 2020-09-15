package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import duke.command.CommandExecutor;
import duke.command.DukeCommandExecutor;
import duke.exception.DukeException;
import duke.exception.InvalidSaveFileException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.storage.DukeStorage;
import duke.storage.Storage;
import duke.task.TaskArrayList;
import duke.ui.Ui;

// Main class that initializes the program.
public class Duke {
    private static final String START_MSG = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final Path DEFAULT_SAVE_PATH = Paths.get("data", "duke.txt");
    private static final String ERROR_LOAD_SAVE = "An error has occurred while loading the save file!";

    private final Scanner sc = new Scanner(System.in);
    private final CommandExecutor exe = new DukeCommandExecutor();
    private final Ui ui = new Ui();
    private final Path savePath;
    private final Storage storage;
    private final TaskArrayList taskList;

    /**
     * Initialises the Duke chatbot with a default save path.
     *
     * @throws IOException If an IO error occurs while loading the save file.
     */
    public Duke() throws IOException {
        this.savePath = DEFAULT_SAVE_PATH;
        this.storage = new DukeStorage(savePath);
        this.taskList = new TaskArrayList(storage);
    }

    /**
     * Initialises the Duke chatbot with the given save path.
     *
     * @throws IOException If an IO error occurs while loading the save file.
     */
    public Duke(Path savePath) throws IOException {
        this.savePath = savePath;
        this.storage = new DukeStorage(savePath);
        this.taskList = new TaskArrayList(storage);
    }

    /**
     * Starts the program on the cli.
     */
    public void runCli() throws InvalidSaveFileException {
        try {
            loadSave();
        } catch (DukeException e) {
            throw new InvalidSaveFileException(ERROR_LOAD_SAVE);
        }

        ui.print(new NormalResponse(START_MSG));
        while (true) {
            String input = sc.nextLine().trim();
            Response msgBody = exe.execute(input, taskList);
            ui.print(msgBody);
            if (exe.shouldExit()) {
                break;
            }
        }
    }

    protected Response getResponse(String input) {
        return exe.execute(input, taskList);
    }

    protected void loadSave() throws DukeException {
        List<String> loadedLines = storage.getSavedLines();
        for (String line: loadedLines) {
            exe.loadSaveString(line, taskList);
        }
    }

    protected boolean shouldExit() {
        return exe.shouldExit();
    }

    public static void main(String[] args) throws IOException, InvalidSaveFileException {
        new Duke(DEFAULT_SAVE_PATH).runCli();
    }
}
