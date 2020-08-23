public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui;

    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = storage.loadData(ui);
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
            } catch (DukeError e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
