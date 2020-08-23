import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Cait {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Cait(String fileName) {
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
        new Cait("duke_data.txt").run();
    }
}
