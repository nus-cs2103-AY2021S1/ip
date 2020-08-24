import data.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;


public class Duke {
    private String filePath;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser(tasks, storage);
    }

    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            ui.displayLine();
            String fullInput = ui.readCommand();
            parser.parse(fullInput);
            isExit = parser.isExit();
        }
        ui.displayBye();

    }

    public static void main(String[] args) {
        new Duke(".\\data.txt").run();
    }
}
