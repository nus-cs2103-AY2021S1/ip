package duke;

import java.util.Scanner;

/**
 * Main class for duke to run.
 */
public class Duke {
    protected static TaskList tasks;
    protected static ArchivedTaskList archivedTasks;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises Duke.
     *
     * @param filePath A string of file path to store the task list.
     */
    public Duke(String filePath) {
        assert !filePath.isEmpty() : "Data filePath is missing.";
        String archivedFilePath = filePath + "/../archivedTasks.txt";
        ui = new Ui();
        storage = new Storage(filePath, archivedFilePath);
        tasks = new TaskList(storage.load());
        archivedTasks = new ArchivedTaskList(storage.loadArchivedTasks());
    }

    /**
     * Initialises required objects.
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        String archivedFilePath = "data/archivedTasks.txt";
        ui = new Ui();
        storage = new Storage(filePath, archivedFilePath);
        tasks = new TaskList(storage.load());
        archivedTasks = new ArchivedTaskList(storage.loadArchivedTasks());
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
            assert !inputMsg.isEmpty() : "Input command should not be empty.";
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
        assert !input.isEmpty() : "Input command should not be empty.";
        return Parser.parseUserInput(input);
    }

    /**
     * Saves the taskList into the data file.
     */
    public void save() {
        storage.writeTasks(tasks);
        storage.writeArchivedTasks(archivedTasks);
    }
}
