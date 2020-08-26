public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    public void run() {
        ui.showWelcome();
        ui.responder();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
