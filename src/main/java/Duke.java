import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            ui.getError(e);
            ui.say("It seems like you have no saved files! Creating one now...");
            tasks = new TaskList();
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
}