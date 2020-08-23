package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Main class for the Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String pathname) {
        this.ui = new Ui();
        this.storage = new Storage(pathname);
        this.taskList = new TaskList(new ArrayList<>());
    }

    /**
     * Start the chat bot
     */
    public void run() {
        Parser parser = new Parser(new Scanner(System.in));
        try {
            this.storage.load(taskList);
        } catch (FileNotFoundException e) {
            this.ui.printReply("OOPS!!! Can't access task data.");
        } catch (IOException e) {
            this.ui.printReply("OOPS!!! Something went wrong... Tasks not saved.");
        }
        this.ui.greet();
        while (true) {
            String reply = parser.executeCommand(this.taskList);
            if (reply.equals("bye")) {
                break;
            }
            this.ui.printReply(reply);
            try {
                this.storage.save(this.taskList);
            } catch (IOException e) {
                this.ui.printReply("OOPS!!! Something went wrong... Tasks not saved.");
            }
        }
        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}