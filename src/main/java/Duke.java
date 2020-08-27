package main.java;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = TaskList.fromData(storage.read());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.hi();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            storage.write(tasks.toData());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}