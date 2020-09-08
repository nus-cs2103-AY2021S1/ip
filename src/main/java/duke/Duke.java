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
        ui.getLogoMsg();
        ui.getGreetingMsg();
        handleInput();
        save();
        ui.getByeMsg();
    }

    private void handleInput() {
        Scanner sc = new Scanner(System.in);
        String inputMsg = sc.nextLine();

        while (!inputMsg.equals("bye")) {
            Parser.parseUserInput(inputMsg);
            // waiting for user to key in the next request
            inputMsg = sc.nextLine();
        }
    }

    /**
     * Starts the program.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Gets the reply message.
     *
     * @param input User's input.
     * @return Reply message.
     */
    public String getResponse(String input) {
        return Parser.parseUserInput(input);
    }

    /**
     * Saves the taskList into the data file.
     */
    public void save() {
        storage.writeTasks(tasks);
    }
}
