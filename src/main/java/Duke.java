import command.Command;
import exceptions.DukeException;
import task.TaskList;

public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Creates Duke object by loading tasks stored locally in filePath.
     * @param filePath File path to .txt file to load tasks stored locally.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(storage.tasks);
    }

    /**
     * Creates Duke object to be used by JavaFX.
     */
    public Duke() {
        this("duke.txt");
    }

    /**
     * Runs Duke.
     * @throws DukeException
     */
    public void run() throws DukeException {
        // displayed once Duke is run, without input from user
        this.ui.greetings();
        Parser parser = new Parser(this);
        boolean isExit = false;

        while (!isExit) {
            String input = this.ui.readCommand();
            try {
                Command command = parser.parse(input, this.taskList);
                this.ui.showLine();
                command.execute();
                isExit = command.isExit;
            } catch (DukeException ex) {
                System.out.println(ex);
            }

            if (!isExit) {
                this.ui.showLine();
            }
        }

        // Save tasks before terminating program
        this.storage.saveTasks(this.taskList.tasks);
        this.ui.goodbye();
    }

    /**
     * Runs Duke program.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("duke.txt");
        duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Your Dark Side: " + input;
    }
}



