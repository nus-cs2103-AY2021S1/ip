public class DukeController {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    protected DukeController() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.getTasks());
    }

    protected void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }
}
