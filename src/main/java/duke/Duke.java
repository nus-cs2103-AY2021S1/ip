package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor specifying filepath.
     * @param filepath
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of Duke.
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void run() throws DukeException, IOException, ClassNotFoundException {
        ui.start(storage);
    }

    /**
     * Main method.
     * @param args
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws DukeException, IOException, ClassNotFoundException {
        new Duke("store.ser").run();
    }
}

