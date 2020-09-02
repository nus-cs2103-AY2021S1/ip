package duke;

/**
 * Class contains main method of the Duke application.
 * Duke manages, stores and track tasks as specified by the user.
 */
public class Duke {
    private static String TASKS_PATHNAME = "data/tasks.txt";
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor which takes in file path of the storage file.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * main method of Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke(TASKS_PATHNAME).run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
