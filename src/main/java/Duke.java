import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {
    private static final String DATABASE_DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"), "../../../", "data").toString();
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String databaseDirectoryPath) {
        this.ui = new Ui();
        this.storage = new Storage(databaseDirectoryPath);
        this.parser = new Parser();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage("Could not load saved tasks");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.greetUser();

        boolean keepApplicationRunning = true;
        Scanner sc = new Scanner(System.in);

        while (keepApplicationRunning) {
            try {
                String userInput = this.ui.getUserInput(sc);
                if (userInput.equals("bye")) {
                    keepApplicationRunning = false;
                }
                this.parser.parseCommands(this.tasks, userInput, sc);
                this.storage.save(this.tasks.getDatabase()); // Saves the state after each command instead of only saving to the database upon exit
            } catch (DukeException e) {
                this.ui.showErrorMessage(e.getMessage());
            }
        }

        this.ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke(Duke.DATABASE_DIRECTORY_PATH).run();
    }
}
