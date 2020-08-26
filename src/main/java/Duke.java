package main.java;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Duke(String fileName) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(fileName);
            this.tasklist = storage.formTaskList();
        } catch (IOException error) {
            System.out.println(error.toString());
        }
    }

    /**
     * Driver function
     */
    public void run() {
        try {
            Processor.process(tasklist, storage, ui);
        } catch (DukeException dukeException) {
            ui.printError(dukeException);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
