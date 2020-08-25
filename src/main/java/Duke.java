import java.util.Scanner;

/**
 * Defines the Duke class for the end-user to manage tasks.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Command command;

    Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        this.tasks = new TaskList(storage.loadData());
        this.command = new Command();
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/tasks.txt").run();
    }

    /** Runs the Duke program. */
    public void run() {
        command.welcomeCommand();
        Scanner scanner = new Scanner(System.in);
        boolean terminated = false;

        while (!terminated && scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                command.execute(userInput, tasks, storage);
                terminated = command.isTerminated();
            } catch (DukeException ex) {
                ui.showErrorMessage(ex.getMessage());
            } finally {
                ui.showLineBreak();
            }
        }
        scanner.close();
    }
}
