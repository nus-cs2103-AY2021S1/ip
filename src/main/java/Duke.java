public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        this.ui = new Ui(this.tasks);
        this.parser = new Parser(this.ui, this.tasks);
    }

    public void run() {
        ui.showWelcome();
        this.parser.responder();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
