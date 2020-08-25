package duke;

public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskManager taskManager;

    public Duke() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    public void run() {
        this.ui.printWelcomeMessage();
        this.taskManager.manage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
