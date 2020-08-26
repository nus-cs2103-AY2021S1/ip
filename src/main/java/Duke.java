public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.logoMsg();
        ui.greetingMsg();
        Parser.parseUserInput(tasks);
        storage.writeTasks(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
