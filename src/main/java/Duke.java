import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    String filePath;

    public Duke(String filePath) throws FileNotFoundException {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks = storage.readFile();
        this.filePath = filePath;
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();
        parser.parser(tasks, filePath);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
