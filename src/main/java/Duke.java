import java.nio.file.Path;


public class Duke {

    public static final Path path = java.nio.file.Paths.get(".", "data.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(path).run();
    }
}
