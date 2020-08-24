import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException de) {
            ui.printLoadingError(de);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();

        String input;
        boolean isEnd = false;

        try {
            while (!isEnd) {
                input = ui.readCommand();
                isEnd = Parser.execute(tasks, ui, storage, input);
            }
        } catch (DukeException de) {
            ui.printLoadingError(de);
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
