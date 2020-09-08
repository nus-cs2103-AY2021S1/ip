package duke;

import java.util.Scanner;

/**
 * Main class for duke to run.
 */
public class Duke {
    protected static TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises Duke.
     *
     * @param filePath A string of file path to store the task list.
     */
    public Duke(String filePath) {
        assert !filePath.isEmpty() : "Data filePath is missing.";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * asd
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.logoMsg();
        ui.greetingMsg();

        Scanner sc = new Scanner(System.in);
        String inputMsg = sc.nextLine();

        while (!inputMsg.equals("bye")) {
            assert !inputMsg.isEmpty() : "Input command should not be empty!";

            Parser.parseUserInput(inputMsg);
            // waiting for user to key in the next request
            inputMsg = sc.nextLine();
        }
        storage.writeTasks(tasks);
        // say bye to the user
        Ui.byeMsg();
    }

    /**
     * Starts the program.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        assert !input.isEmpty() : "Input command should not be empty!";
        return Parser.parseUserInput(input);
    }
    public void save() {
        storage.writeTasks(tasks);
    }
}
