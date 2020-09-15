public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.tasks = new TaskList();

        try {
            storage.loadData(tasks);
            ui.fileLoaded();
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.sayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String nextCommand = this.ui.getNextCommand();
                Command c = Parser.parse(nextCommand, tasks);
                if (c != null) {
                    c.execute(this.ui, this.storage, this.tasks);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                ui.showErrorMsg(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}