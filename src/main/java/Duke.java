import java.io.*;

/**
 * <h1>Duke Task Tracker</h1>
 * The Duke program implements a bot that will help
 * users keep track of the different tasks they input.
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        storage = new Storage();
        try {
            // storage.clear();
            taskList = new TaskList(storage.readData());
            ui = new Ui(taskList, storage);
        } catch (IOException e) {
            taskList = new TaskList();
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        ui.startProgram();
    }

    /**
     * This is the main method which will start running the
     * Duke Task Tracker.
     * @exception IOException On input error.
     * @param args Unused.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
