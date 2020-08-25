public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.initializeTasks());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | TaskException e) {
                ui.formatResponse(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
