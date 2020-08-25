package duke.main;

public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = storage.getTasksFromFile();
        this.ui = new Ui(taskList);
    }

    private void run() {
        ui.run();
        storage.write(taskList);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}