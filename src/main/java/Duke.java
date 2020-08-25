import java.io.FileNotFoundException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    String filePath;

    /**
     * Initialise Duke with the filePath.
     * @param filePath the path to save/read output.
     * @throws FileNotFoundException for if file is not found.
     */
    public Duke(String filePath) throws FileNotFoundException {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks = storage.readFile();
        this.filePath = filePath;
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Runs the Duke by initialise the Parser.
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(ui);
        parser.parser(tasks, filePath);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("./duke.txt").run();
    }
}
