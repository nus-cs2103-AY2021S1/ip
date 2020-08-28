package duke;

import duke.command.CommandExecutor;
import duke.command.DukeCommandExecutor;
import duke.exception.DukeException;
import duke.exception.InvalidSaveFileException;
import duke.task.TaskArrayList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Scanner;

// Main class that initializes the program.
public class Duke {
    private final Scanner SC = new Scanner(System.in);
    private final CommandExecutor EXE = new DukeCommandExecutor();
    private final Ui UI = new Ui();
    private final Storage STORAGE;
    private final TaskArrayList TASK_LIST;


    public Duke(Path filePath) throws IOException {
        this.STORAGE = new Storage(filePath);
        this.TASK_LIST = new TaskArrayList(STORAGE);
    }

    private void handleStart() {
        UI.print("Hello! I'm Duke\nWhat can I do for you?");
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

        String input, msgBody;

        while (true) {
            input = SC.nextLine().trim();
            try {
                msgBody = EXE.execute(input, TASK_LIST);
                UI.print(msgBody);
                if (EXE.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                UI.printErr(e.getMessage());
            }
        }
    }

    private void initialise() throws DukeException {
        handleStart();
        List<String> loadedLines = STORAGE.getSavedLines();
        for (String line: loadedLines) {
            EXE.loadSaveString(line, TASK_LIST);
        }
    }

    public static void main(String[] args) throws IOException, InvalidSaveFileException {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}
