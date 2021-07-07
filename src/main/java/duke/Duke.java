package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.GraphicalUi;
import duke.ui.TerminalUi;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
 * The user may choose to run this chat bot
 * via the Terminal (via src/main/java/duke/Duke) by choosing Run Duke.main(),
 * or on a Graphical User Interface (via src/main/java/duke/Launcher by choosing Run Launcher.main().
 * Contains static attribute stored_task which stores task input from user.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isInProgram;

    /**
     * Initializes Duke containing the storage, terminal ui and task list. For Duke running with Terminal UI.
     *
     * @param filePath Filepath of where storage files are stored.
     * @param ui       Terminal User Interface in Duke.
     */
    public Duke(String filePath, TerminalUi ui) {
        this.storage = new Storage(filePath);
        this.ui = ui;
        this.isInProgram = true;
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.processError(e.getMessage());
        }
    }

    /**
     * Initializes Duke containing the storage, graphical ui and task list. For Duke running with GUI.
     *
     * @param filePath Filepath of where storage files are stored.
     * @param ui       Graphical User Interface in Duke.
     */
    public Duke(String filePath, GraphicalUi ui) {
        this.storage = new Storage(filePath);
        this.ui = ui;
        this.isInProgram = true;
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList(new ArrayList<>());
            ui.setErrorGreeting(e.getMessage());
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Runs Duke chat bot on the Terminal.
     */
    public void run() {
        TerminalUi terminalUi = ((TerminalUi) ui);
        terminalUi.greet();
        while (isInProgram) {
            try {
                String input = terminalUi.getNextInput();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isInProgram = command.isInProgram();
            } catch (DukeException e) {
                ui.processError(e.getMessage());
            }
        }
    }

    /**
     * Generates response from user input.
     *
     * @param input User input.
     * @return Response from the user's input.
     */
    public String getResponse(String input) {
        try {
            assert (isInProgram) : "Duke should still be running.";
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            isInProgram = command.isInProgram();
        } catch (DukeException e) {
            ui.processError(e.getMessage());
        }
        return ((GraphicalUi) ui).getResponseMessage();
    }

    /**
     * Checks if GUI currently contains an error message.
     *
     * @return If GUI currently contains an error message.
     */
    public boolean hasErrorInGui() {
        return ((GraphicalUi) ui).hasErrorMessage();
    }

    public boolean isInProgram() {
        return isInProgram;
    }

    /**
     * Run the Duck chat bot via the Terminal.
     * Creates a duke object named duck with the storage file path and TerminalUi object, and runs it.
     **/
    public static void main(String[] args) {
        Duke duck = new Duke("data/duke.txt", new TerminalUi());
        duck.run();
    }
}
