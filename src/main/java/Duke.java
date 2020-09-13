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

    public String getResponse(String input) {
        try {
            return command.execute(input, tasks, storage);
        } catch (DukeException ex) {
            return ui.printErrorMessage(ex);
        }
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/tasks.txt").run();
    }

    /**
     * For running the Duke program in terminal.
     */
    public void run() {
        command.welcomeCommand();
        Scanner scanner = new Scanner(System.in);
        boolean isTerminated = false;

        while (!isTerminated && scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                command.execute(userInput, tasks, storage);
                isTerminated = command.isTerminated();
            } catch (DukeException ex) {
                ui.printErrorMessage(ex);
            } finally {
                ui.printLineBreak();
            }
        }
        scanner.close();
    }
}
