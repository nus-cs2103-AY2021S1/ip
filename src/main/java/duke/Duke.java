package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import duke.action.Action;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a text-based bot that tracks different types of tasks.
 * Supports the creation of Todo, Deadline & Event Tasks
 * as well as other commands such as search, marking as done etc.
 */
public class Duke {

    public static final String DEFAULT_SAVE_FILE = ".\\data\\duke.txt";
    public static final int MAX_NUM_OF_TASKS = 100;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Queue<Action> actionQueue;

    /**
     * Constructs a Duke object and initialises Ui, Storage & TaskList classes.
     * @param filePath Path of local save file for Duke's task list.
     */
    private Duke(String filePath) {
        ui = new Ui();
        actionQueue = new LinkedList<>();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui.greetingMessage();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Public, no-args constructor for JavaFX use only.
     */
    public Duke() {
        this (DEFAULT_SAVE_FILE);
    }

    /**
     * Starts running process of Duke.
     * While running, reads user input through Ui,
     * Parser determines the associated Command given,
     * The Command is executed and TaskList is saved to drive.
     * This method also encapsulates each reply from Duke with a
     * line separator above and below.
     */
    public void run() {
        boolean running = true;
        while (running) {
            try {
                String userInput = ui.readUserInput();
                ui.showLine(); // show the divider line ("######")
                if (actionQueue.size() != 0) {
                    Action a = actionQueue.peek();
                    Optional<Action> next = a.receiveInputAndGetNextAction(
                            userInput);
                    actionQueue.poll(); //Action removed if successfully completed.
                    next.ifPresent(x-> x.prompt(ui));
                    next.ifPresent(actionQueue::add);

                } else {
                    Command c = Parser.parse(userInput);
                    c.execute(ui, storage, tasks, actionQueue);
                    if (c.isExit()) {
                        running = false;
                    }
                }

                storage.store(tasks);
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method of Duke.
     */
    public static void main(String[] args) {
        new Duke(DEFAULT_SAVE_FILE).run();
    }

    /**
     * Function to get String response from Duke.
     * @param input String input from user.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        try {
            if (actionQueue.size() != 0) {
                Action a = actionQueue.peek();
                Optional<Action> next = a.receiveInputAndGetNextAction(input);
                actionQueue.poll(); // remove action if successfully completed.
                next.ifPresent(x -> x.prompt(ui));
                next.ifPresent(actionQueue::add);
            } else {
                Command c = Parser.parse(input);
                c.execute(ui, storage, tasks, actionQueue);
            }
            storage.store(tasks);

            // Put things back
            System.out.flush();
            System.setOut(old);

            return baos.toString();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
