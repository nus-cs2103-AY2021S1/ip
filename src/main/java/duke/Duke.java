package duke;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import exception.MissingInfoException;

import javafx.stage.Stage;

/**
 * Main class for the Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Stage stage;

    /**
     * Initializes a Duke object with saved tasks from the given data file.
     *
     * @param pathname The path of the data file.
     */
    public Duke(String pathname) {
        this.ui = new Ui();
        this.storage = new Storage(pathname);
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
        } catch (MissingInfoException e) {
            this.ui.printReply(e.getMessage());
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
            } catch (MissingInfoException e) {
                this.ui.printReply(e.getMessage());
            }
        }

        this.ui.bye();
    }

    /**
     * Sets the Stage object in duke for the GUI.
     *
     * @param stage The stage object
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the Stage object stored in Duke.
     *
     * @return The Stage object.
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Returns the Storage object stored in Duke.
     *
     * @return The Storage object.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the Ui object stored in Duke.
     *
     * @return The Ui object.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns the Parser object stored in Duke.
     *
     * @return The Parser object.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Returns the TaskList object stored in Duke.
     *
     * @return The TaskList object.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}