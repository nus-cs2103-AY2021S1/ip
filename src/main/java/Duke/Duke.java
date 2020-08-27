package Duke;

import Duke.Command.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * Duke class that contains the main logic for the chat bot DUKE.
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String path) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(path);
        TaskList tempTaskList;
        try {
            tempTaskList = new TaskList(storage);
        } catch (DukeException | IOException e) {
            tempTaskList = new TaskList();
        }
        this.tasklist = tempTaskList;
    }

    public void run() {
        ui.showIntro();
        boolean isExited = false;
        while (!isExited) {
            try {
                String line = ui.readLine();
                ui.lineDivider();
                Command command = Parser.parse(line);
                command.execute(tasklist, ui, storage);
                isExited = command.isExited();
            } catch (IOException e){
                ui.showFileError();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.lineDivider();
            }
        }
    }

    /**
     * Main logic for the chat bot
     * @param args User input
     */
    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir").endsWith("text-ui-test")
                ? "test.txt"
                : System.getProperty("user.dir").endsWith("ip")
                ? "data/duke.txt"
                : System.getProperty("user.home") + "/duke.txt";
        try {
            new Duke(filePath).run();
        } catch (IOException e) {
            System.out.println("File not recognised\n");
        }
    }

}
