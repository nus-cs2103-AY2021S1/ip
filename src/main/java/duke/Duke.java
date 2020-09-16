package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class that runs the Duke program
 */
public class Duke {

    private static TaskList tasks;
    private final Storage storage;

    /**
     * Constructor for creating Duke object
     *
     * @param filePath relative directory of the storage file
     */
    public Duke(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input
     * @param input user input
     * @return duke's reply
     * @throws IOException if filePath does not exist
     */
    public String getResponse(String input) throws IOException {
        Parser.setTasks(tasks);
        Parser.setStorage(storage);
        return Parser.parse(input);
    }

    /**
     * Getter to retrieve tasks
     *
     * @return list of tasks
     */
    public static TaskList getTasks() {
        return tasks;
    }

    public void run() throws IOException {
        Parser.setTasks(tasks);
        Parser.setStorage(storage);
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String command = sc.nextLine();
            System.out.println("_____________________________________");
            System.out.println(Parser.parse(command));
            isExit = Parser.getExitStatus();
            System.out.println("_____________________________________");
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("./duke.txt").run();
    }
}
