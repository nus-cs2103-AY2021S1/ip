public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isQuitting;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isQuitting = false;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.chatPrint(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.greet();
        while (!this.isQuitting) {
            try {
                Command c = Parser.parse(this.ui.getInput());
                c.execute(this.tasks, this.ui, this.storage);
                this.isQuitting = c.isQuitting();
            } catch (DukeException e) {
                ui.chatPrint(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}