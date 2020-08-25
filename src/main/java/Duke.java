public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readList());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.giveResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String path = "data/tasks.txt";
        new Duke(path).run();
    }
}
