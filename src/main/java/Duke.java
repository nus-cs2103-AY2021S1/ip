public class Duke {

    private final Storage STORAGE;
    private final TaskList TASKS;
    private final Ui UI;

    // constructor
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        TASKS = new TaskList(STORAGE.loadTasks());
    }

    public void run() {

        UI.greet(STORAGE.createResult);

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                Command c = Parser.interpret(fullCommand);
                c.execute(TASKS, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showLine();
                UI.showError(e.getMessage());
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {

        // the argument to the constructor is the file path (relative) where Duke will read and write tasks given to it
        new Duke("src/main/java/tasks.txt").run();

    }
}
