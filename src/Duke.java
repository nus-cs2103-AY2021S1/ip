
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String file) {
        this.ui = new Ui();
        this.storage = new Storage(file);
        this.taskList = new TaskList();
    }

    public void run() {
        Parser.action(taskList, ui, storage);
    }

    public static void main(String[] args) {
        new Duke(".//SAVED-TASKS.txt").run();
    }
}

