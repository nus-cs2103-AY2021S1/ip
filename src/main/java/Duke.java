public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        ui.takeUserInput(tasks);
        try {
            storage.save(tasks);
        } catch (DukeException e){
            ui.say(e.getMessage());
        }
        ui.end();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
