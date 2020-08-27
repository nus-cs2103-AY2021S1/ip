package Duke;

import Duke.Command.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * Represents Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Creates Duke
     * @param path  File path of saved tasks file.
     * @throws IOException
     */
    public Duke(String path) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(path);
        TaskList tempList;
        try {
            tempList = new TaskList(storage);
        } catch (DukeException | IOException e) {
            tempList = new TaskList();
        }
        this.tasklist = tempList;
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readLine();
                ui.lineDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExited();
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
     * Main environment for the chat bot.
     * @param args  User input
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
