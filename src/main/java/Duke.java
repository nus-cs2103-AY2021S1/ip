public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                ui.showBlankLine();
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitProgram = c.isExit();
            } catch (DukeException err) {
                ui.showError(err.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
