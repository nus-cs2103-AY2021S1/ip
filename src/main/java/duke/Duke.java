package duke;

import java.util.ArrayList;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various tasks.
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
     * @param ui Terminal User Interface in Duke.
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
     * @param ui Graphical User Interface in Duke.
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
     * Runs Duke chat bot.
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
            assert isInProgram;
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
     * Creates a duke object named duck and runs it.
     **/
    public static void main(String[] args) {
        Duke duck = new Duke("data/duke.txt", new TerminalUi());
        duck.run();
    }
}
