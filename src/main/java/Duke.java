public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // Constructor
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    // Receives commands from user input and executes them accordingly
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputData = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(inputData);
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
        Duke dukeObj = new Duke("data/duke.txt");
        dukeObj.run();
    }
}