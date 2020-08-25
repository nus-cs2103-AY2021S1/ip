package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException, DukeException {
        Parser parser = new Parser(ui);
        parser.interact(tasks, Storage.getFilePath());
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
