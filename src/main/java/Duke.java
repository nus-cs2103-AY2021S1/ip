import Parser.Parser;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String fileName) {
        storage = new Storage(fileName);
        tasks = new TaskList(storage);
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = ui.isExit();
        while (!isExit) {
            ui.readInput();
            isExit = ui.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("duke_data.txt").run();
    }
}
