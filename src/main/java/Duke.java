public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    private void run() {
        ui.printGreet();
        while (ui.input.hasNextLine()) {
            String command = ui.getInput();
            Parser.processCommand(command, ui, tasks, storage.filePath);
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke manager = new Duke(filePath);
        manager.run();
    }

}
