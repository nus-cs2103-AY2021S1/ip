package main.java;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    private void takeUserInput() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readLine();
                Command command = Parser.parseUserInput(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    private void run() {
        ui.welcome();
        takeUserInput();
        ui.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}