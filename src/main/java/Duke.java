/**
 * Returns typing box to allow user to type command.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates ui, read date from file.
     * Acts as default state if no data file is to be read.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("./data/duke.txt");
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Instantiates ui, read date from file.
     * @param filePath file path of the file to be read.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Reads the user input, parse it to parser, call correspond command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException | DoneException | DeleteException
                    | TodoException | EventException | DeadlineException | FindException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | DoneException | DeleteException
                | TodoException | EventException | DeadlineException | FindException e) {
            return e.getMessage();
        }
    }
}
