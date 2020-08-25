public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        try {
            this.storage = new Storage(filePath);
            storage.loadData(tasks);
        } catch (DukeException e) {
            ui.showErrorLoad(e.getMessage());
            tasks.empty();
        }
    }

    /**
     * Executes the programme provided that Duke has been initialized.
     * Following the convention for user input is crucial for an expected behavior.
     * Exception will be thrown upon undefined user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
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
        new Duke("data/tasks.txt").run();
    }
}
