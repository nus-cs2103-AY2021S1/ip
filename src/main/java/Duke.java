import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Command command;

    public Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        this.tasks = new TaskList(storage.loadData());
        this.command = new Command();
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/tasks.txt").run();
    }

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
