package duke;

import java.util.Scanner;

/**
 * A task manager.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Duke constructor.
     *
     * @param filePath path of the storage file.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            Ui.showUpperLine();
            Ui.showMessage(e.getMessage());
            Ui.showLowerLine();
        }
    }

    /**
     * Runs the Duke.
     */
    public void run() {
        Ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                Ui.showUpperLine();
                Command next = Parser.parse(input);
                next.execute(taskList, storage);
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            } finally {
                Ui.showLowerLine();
                input = scanner.nextLine();
            }
        }

        Ui.showGoodbye();
        scanner.close();
    }

    /**
     * Entry point of the Duke's program.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        new Duke("./data/dukeFile.txt").run();
    }
}
