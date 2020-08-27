public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    // constructor
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.greet(storage.createResult);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.interpret(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLine();
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        // the argument to the constructor is the file path (relative) where Duke will read and write tasks given to it
        new Duke("src/main/java/tasks.txt").run();

    }
}
