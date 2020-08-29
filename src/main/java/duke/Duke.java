package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidInstructionLengthException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.MissingFieldException;
import duke.exception.TaskDoneException;
import duke.exception.UnknownInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UiManager;
import duke.logic.UserInputParser;

/**
 * Represents a Duke Chat bot.
 * It contains a <code>TaskList</code> to track the user's tasks,
 * a <code>UIManager</code> to handle user interactions and
 * a <code>StorageManager</code> to handle storing of data.
 */
public class Duke {
    private final UiManager uiManager;
    private final StorageManager storageManager;
    private TaskList taskList;

    public Duke() {
        this.uiManager = new UiManager();
        this.storageManager = new StorageManager(CommonString.DUKE_FILE_PATH.toString());
        try {
            this.taskList = new TaskList(storageManager.loadData());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Load Data: " + e.getMessage());
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
    }


    /**
     * Executes Main method for Duke
     * Initialises <code>Duke</code> object and runs.
     */
    public static void main(String[] args) {
        // Initialisation of duke.Duke
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Executes Duke Program.
     * User input is extracted by <code>UIManager</code> and parsed by <code>UserInputParser</code>,
     * generating a command. Execution of commands are managed by individual <code>Command</code> instances.
     * Storing/Loading of data is managed by <code>StorageManager</code>.
     */
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
            } catch (UnknownInstructionException | InvalidInstructionFormatException
                    | MissingFieldException | TaskDoneException
                    | InvalidInstructionLengthException | InvalidTaskIndexException e) {
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
