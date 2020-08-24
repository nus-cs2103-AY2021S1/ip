import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(new File(filePath)));
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.greeting();
        ui.run();
    }

    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        boolean fileExists = new java.io.File(home + "/iP/File.txt").exists();
        new Duke(home + "//iP//File.txt").run();
    }
}
