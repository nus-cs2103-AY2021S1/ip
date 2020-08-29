package duke;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class for the Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String pathname) {
        this.ui = new Ui();
        this.storage = new Storage(pathname);
        this.taskList = new TaskList(new ArrayList<>());
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.taskList = new TaskList(new ArrayList<>());
        this.parser = new Parser(new Scanner(""));
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }


    public Parser getParser() {
        return this.parser;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}