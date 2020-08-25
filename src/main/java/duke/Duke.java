package duke;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui = new Ui();
        } catch (DukeException e) {
            assert false;
            ui.showLoadingError(e);
        }
    }

    public void run() {
        ui.initializeDukeUI(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
