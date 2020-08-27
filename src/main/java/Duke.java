public class Duke {

    private final Storage storage;
    private  TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingErrorMessage();
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.greet();

        boolean isExit = false;

        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.wrapMessage(e.toString());
            }

        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}