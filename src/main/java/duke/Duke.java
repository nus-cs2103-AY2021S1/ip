package duke;

import duke.command.Command;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidInstructionException;
import duke.exception.MissingFieldException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
import duke.logic.UserInputParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private final UIManager uiManager;
    private final StorageManager storageManager;
    private TaskList taskList;

    public Duke() {
        this.uiManager = new UIManager();
        this.storageManager = new StorageManager(CommonString.DUKE_FILE_PATH.toString());
        try {
            this.taskList = new TaskList(storageManager.loadData());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Load Data: " + e.getMessage());
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    // MAIN FUNCTION
    public static void main(String[] args) {
        // Initialisation of duke.Duke
        Duke duke = new Duke();
        duke.run();
    }

    // METHOD TO RUN DUKE PROCESSES
    public void run() {
        // INTRO
        uiManager.printDukeIntro();
        boolean isExit = false;

        // Execute duke.Duke Functions
        while (!isExit) {
            try {
                String userInput = uiManager.readCommand();
                Command command = UserInputParser.parse(userInput);
                command.execute(taskList, uiManager, storageManager);
                isExit = command.getExitStatus();
            } catch (InvalidInstructionException | MissingFieldException | InvalidFormatException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
                e.printStackTrace();
            }
            uiManager.printLine();
        }

        // OUTRO
        uiManager.printDukeOutro();
    }
}