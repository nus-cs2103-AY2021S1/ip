import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
        }
        try {
            taskList = new TaskList(storage.load(new ArrayList<Task>()));
            this.parser = new Parser(taskList);
            this.ui = new Ui(parser);
        } catch (IOException e) {
            this.parser = new Parser(taskList);
            this.ui = new Ui(parser);
        }
    }

    public void run() {
        ui.intro();
        while (ui.getContinue()) {
            ui.getNewInput();
        }
        ui.bye();
    }
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}