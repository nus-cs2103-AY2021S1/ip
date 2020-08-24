package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static TaskList getTasks() {
        return tasks;
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Parser.parse(fullCommand);
            isExit = Parser.getExitStatus();
            if (!isExit) {
                ui.showLine();
            }
        }
        storage.clear();
        storage.save(tasks);
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
