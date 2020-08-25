import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            ui.getError(e);
            this.tasks = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean isBye = false;
        while (!isBye) {
            String input = ui.receive();
            try {
                Parser.process(input, ui, tasks, storage);
            } catch (DukeException | IOException e) {
                ui.getError(e);
            } finally {
                isBye = Parser.isBye(input);
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}