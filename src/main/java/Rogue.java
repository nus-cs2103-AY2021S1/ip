public class Rogue {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rogue(String filePath) {
        storage = Storage.init(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
        ui = new Ui();
    }

    public void start() {
        ui.greet();
        boolean isExit = false;
        do {
            String fullCommand = ui.readCommand();
            try {
                Executable e = Parser.createExe(fullCommand);
                Report r = e.execute(storage, tasks, ui);
                ui.print(r);
                isExit = r.isExit();
            } catch (UnknownCommandException | IncorrectArgumentException | StorageException e) {
                ui.print(e.getMessage());
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        new Rogue("./data/tasks.txt").start();
    }
}
