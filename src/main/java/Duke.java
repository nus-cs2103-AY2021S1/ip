public class Duke {

    private Storage storage;

    private TaskManager manager;

    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            manager = new TaskManager(storage.loadTasks(), ui);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager(ui);
        }
    }

    public void run() {
        ui.showWelcomeMessage();   
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                manager.handleInput(input);
                storage.saveTasks(manager.getTasks());
                isExit = manager.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
