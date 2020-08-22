import java.util.List;

public class Duke {
    private static String FILE_PATH = "src/main/data/input.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("src/main/data/input.txt");
        duke.ui.initialize(duke.tasks);
    }

}
