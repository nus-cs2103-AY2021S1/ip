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
import duke.storage.DukeStorage;
import duke.storage.Storage;
import duke.task.TaskArrayList;
import duke.ui.Ui;

// Main class that initializes the program.
public class Duke {
    private final Scanner sc = new Scanner(System.in);
    private final CommandExecutor exe = new DukeCommandExecutor();
    private final Ui ui = new Ui();
    private final Storage storage;
    private final TaskArrayList taskList;

    /**
     * Takes in a path which decides where the save file is stored and initializes the program.
     *
     * @param filePath Path path to save file.
     * @throws IOException if an error occurs while performing IO operations.
     */
    public Duke(Path filePath) throws IOException {
        this.storage = new DukeStorage(filePath);
        this.taskList = new TaskArrayList(storage);
    }

    private void handleStart() {
        ui.print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Starts the program by calling the relevant initialization processes then starts taking in user inputs.
     */
    public void run() throws InvalidSaveFileException {
        try {
            initialise();
        } catch (DukeException e) {
            e.printStackTrace();
            throw new InvalidSaveFileException("An error has occurred while loading the save file!.");
        }

        while (true) {
            String input = sc.nextLine().trim();
            try {
                String msgBody = exe.execute(input, taskList);
                ui.print(msgBody);
                if (exe.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.printErr(e.getMessage());
            }
        }
    }

    private void initialise() throws DukeException {
        handleStart();
        List<String> loadedLines = storage.getSavedLines();
        for (String line: loadedLines) {
            exe.loadSaveString(line, taskList);
        }
    }

    public static void main(String[] args) throws IOException, InvalidSaveFileException {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}
