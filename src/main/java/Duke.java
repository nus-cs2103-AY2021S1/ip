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

    private DukeUi dukeUi;
    private DukeFile dukeFile;
    private ArrayList<Task> arrayLst;
    private Processor processor;

    public Duke(String filePath) {
        this.dukeUi = new DukeUi();
        this.arrayLst = new ArrayList<>();
        this.dukeFile = DukeFile.createDukeFile(filePath);
        this.processor = new Processor();
    }

    /**
     * Initiates the process of DukeBot that
     * accepts user commands and processes
     * the commands to create the Chatbot Task list.
     *
     * @author Lee Penn Han.
     * @return Nothing.
     * @exception IOException on inout error.
     */
    public void run() {
        this.dukeUi.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine().trim().toLowerCase();
            if (!cmd.equals("bye")) {
                try {
                    this.processor.process(cmd, this.arrayLst, this.dukeFile);
                } catch (DukeException e) {
                    this.dukeUi.showError(e.getMessage());
                }
            } else {
                this.dukeUi.goodbyeMessage();
                break;
            }
        }
        try {
            this.dukeFile.saveToFile();
        } catch (IOException e) {
            this.dukeUi.showError(e.getMessage());
        }
    }

    /**
     * This is the main method that makes use of the run method.
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        new Duke("Saved").run();
    }
}
