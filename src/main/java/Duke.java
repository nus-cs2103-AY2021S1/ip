public class Duke {
    private TaskList taskList;
    private Storage storage;
    private final UI ui;

    public Duke(String filepath) {
        ui = new UI();
        taskList = new TaskList();
        try {
            storage = new Storage(filepath);
            storage.load(taskList);
        } catch (StorageException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void run() {
        ui.showHelloMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                String result = command.execute(taskList, storage);
                ui.printResult(result);
                ui.showLine();
                isExit = command.isExit();
            } catch (UIException | DukeUnknownCommandException | StorageException | TaskException e) {
                e.printStackTrace();
            }
        }

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke("./data/taskList.txt").run();
    }
}
