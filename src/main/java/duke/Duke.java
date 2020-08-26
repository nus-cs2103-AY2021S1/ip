package duke;

import duke.command.CommandExecutor;
import duke.command.DukeCommandExecutor;
import duke.exception.DukeException;
import duke.task.TaskArrayList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

// Main class that initializes the program.
public class Duke {
    private final TaskArrayList TASK_LIST = new TaskArrayList();
    private final Scanner SC = new Scanner(System.in);
    private final CommandExecutor EXE = new DukeCommandExecutor();
    private final Ui UI = new Ui();
    private final Storage STORAGE;


    public Duke(Path filePath) throws IOException {
        this.STORAGE = new Storage(filePath);
    }

    private void handleStart() {
        UI.print("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * Starts the program by calling the relevant initialization processes then starts taking in user inputs.
     */
    public void run() {
        handleStart();
        STORAGE.loadSaveFile(TASK_LIST, EXE);
        String input, msgBody;

        while (true) {
            input = SC.nextLine().trim();
            try {
                msgBody = EXE.execute(input, TASK_LIST, STORAGE);
                UI.print(msgBody);
                if (EXE.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                UI.printErr(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke(Paths.get("data", "duke.txt")).run();
    }
}
