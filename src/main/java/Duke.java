import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * <h1> DUKE CLASS </h1>
 * The Duke Class contains the method to
 * initialize the DukeChat bot. Different commands will
 * result in different course of action.
 *
 * Currently Duke only supports Todo, Deadline, Event, Done, Delete, List, Find
 * commands as a Task Tracker.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private ArrayList<Task> arrayLst;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.arrayLst = new ArrayList<>();
        this.storage = Storage.createDukeFile(filePath);
        this.parser = new Parser();
    }

    /**
     * Initiates the process of DukeBot that
     * accepts user commands and processes
     * the commands to create the Chatbot Task list.
     *
     * @author Lee Penn Han.
     */
    public void run() {
        this.ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine().trim().toLowerCase();
            if (!cmd.equals("bye")) {
                try {
                    this.parser.process(cmd, this.arrayLst, this.storage);
                } catch (DukeException e) {
                    this.ui.showError(e.getMessage());
                }
            } else {
                this.ui.goodbyeMessage();
                break;
            }
        }
        try {
            this.storage.saveToFile();
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * This is the main method that makes use of the run method.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("Saved").run();
    }
}
