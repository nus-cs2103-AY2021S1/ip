public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String name) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(taskList, name);
    }

    public void run() {
        ui.startUp(taskList, storage);
        Parser.parseInput(taskList, storage);
    }

    public static void main(String[] args) {
        new Duke("duke").run();
    }
}
