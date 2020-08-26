package duke;

import duke.command.CommandExecutor;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {
    private final TaskList TASK_LIST = new TaskList();
    private final Scanner SC = new Scanner(System.in);
    private final CommandExecutor EXE = new CommandExecutor();
    private final Ui UI = new Ui();
    private final Storage STORAGE;


    public Duke(Path filePath) throws IOException {
        this.STORAGE = new Storage(filePath);
    }

    private void handleStart() {
        UI.print("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

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
