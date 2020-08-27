public class Alison {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Alison(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (AlisonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printBorder();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlisonException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printBorder();
            }
        }
    }

    public static void main(String[] args) {
        new Alison("./data/tasks.txt").run();
    }
}
