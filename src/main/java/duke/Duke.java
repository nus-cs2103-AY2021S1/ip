package duke;

public class Duke {
    private static final String FILE_PATH = (System.getProperty("user.dir").endsWith("text-ui-test") 
        ? System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 13) + "/data/duke.txt"
        : System.getProperty("user.dir") + "/data/duke.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
