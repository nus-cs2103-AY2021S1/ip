package duke;

import java.util.Scanner;

/**
 * Encapsulates a Duke object.
 */
public class Duke {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates a Duke object.
     *
     * @param filePath directory of file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns main logic of code.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String userInput = ui.getUserInput(sc);
                ui.lineBreak();
                Command command = Parser.readUserInput(userInput);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.lineBreak();
            }
        }
    }

    /**
     * Starts Duke programme.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
