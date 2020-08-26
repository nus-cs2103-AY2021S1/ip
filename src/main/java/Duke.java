public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
//        try {
            storage.load(tasks);
//            tasks = new TaskList(storage.load(tasks));
//        }
//        catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.logoMsg();
        ui.greetingMsg();

        Parser.parseUserInput(tasks);
        storage.writeTasks(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();  // storage path need to change
    }
}
