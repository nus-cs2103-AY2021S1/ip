public class Duke {

    private final String name = "Bolot";

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
        ui = new Ui(name);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke("data");
        bot.run();
    }
}