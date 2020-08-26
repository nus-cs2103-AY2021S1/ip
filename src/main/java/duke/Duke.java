package duke;

import java.util.Scanner;

public class Duke {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;

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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
