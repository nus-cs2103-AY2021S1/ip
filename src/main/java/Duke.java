public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException error) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.executeCommand(this.tasks, this.ui, this.storage);
                    isExit = command.isExit();
                }
            } catch (DukeException error) {
                ui.printMessage(error.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (DukeException error) {
            ui.printMessage(error.getMessage());
        }
    }
    

    public static void main(String[] args) {
       new Duke("./tasks.txt").run();
    }
}
