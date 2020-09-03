package duke;

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
     * Initializes Duke containing the storage, ui and task list.
     *
     * @param filePath Filepath of where storage files are stored.
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

    public Duke(String filePath, GraphicalUi ui) throws DukeException {
        this.storage = new Storage(filePath);
        this.ui = ui;
        this.isInProgram = true;
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Runs Duke chat bot.
     */
    public void run() {
        ((TerminalUi) ui).greet();
        while (isInProgram) {
            try {
                String input = ((TerminalUi) ui).getNextInput();
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
     * @return Response which echos the user's input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            isInProgram = command.isInProgram();
        } catch (DukeException e) {
            ui.processError(e.getMessage());
        }
        return ((GraphicalUi) ui).getResponseMessage();
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
