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
            tasks = new TaskList();
            try {
                storage.newStorage();
            } catch (DukeException ex) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }

    public void run() {
        ui.printGreetings();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInputs();
                Command command = Parser.parse(input);
                command.execute(ui, tasks, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        ui.close();
    }


}
