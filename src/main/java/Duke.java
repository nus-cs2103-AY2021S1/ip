import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

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

    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiate a Duke Object with the filepath as that name
     * of the file which the lists will be saved to.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        this.taskList = TaskList.createTaskList();
        this.storage = Storage.createDukeFile(filePath);
    }

    /**
     * Initiates the process of DukeBot that
     * accepts user commands and processes
     * the commands to create the Chatbot Task list.
     *
     * @author Lee Penn Han.
     */
    public void run() {
        Ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine().trim().toLowerCase();
            if (!cmd.equals("bye") && cmd.length() != 0) {
                try {
                    Parser.process(cmd, this.taskList, this.storage);
                } catch (DukeException e) {
                    Ui.showError(e.getMessage());
                }
            } else {
                try {
                    Ui.showSaving();
                    this.storage.saveToFile();
                } catch (IOException e) {
                    Ui.showError(e.getMessage());
                }
                Ui.goodbyeMessage();
                break;
            }
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
