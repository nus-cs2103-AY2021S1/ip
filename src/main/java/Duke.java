import command.Command;
import exceptions.DukeException;
import task.TaskList;

public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;
    protected Parser parser;
    protected boolean hasGreeted;

    /**
     * Creates Duke object by loading tasks stored locally in filePath.
     *
     * @param filePath File path to .txt file to load tasks stored locally.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.tasks);
        this.parser = new Parser(this);
    }

    /**
     * Creates Duke object to be used by JavaFX.
     */
    public Duke() {
        this("duke.txt");
    }

    /**
     * Runs Duke.
     *
     * @throws DukeException
     */
    public String run(String input) throws DukeException {
        String response = null;
        try {
            Command command = parser.parse(input, this.taskList);
            response = command.execute();
        } catch (DukeException ex) {
            response = ex.getMessage();
        }
        if (response.equals("exit")) {
            // Save tasks before terminating program
            this.storage.saveTasks(this.taskList.tasks);
            response = this.ui.goodbye();
        }
        return response;
    }

    /**
     * Runs Duke program.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("duke.txt");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        String response = this.run(input);
        return this.ui.formatResponse(response);
    }

    /**
     * Sets hasGreeted boolean to true;
     */
    void setHasGreeted() {
        this.hasGreeted = true;
    }
}



