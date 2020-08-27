import java.io.File;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String path, String fileName) {
        this.storage = new Storage(path,fileName);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui(this.tasks);
    }

    public void run() throws IOException, EmptyInputException, NoResponseException {
        storage.createFie();
        ui.showWelcome();
        Parser parser = new Parser(this.ui,this.tasks);
        while (!parser.isEnd) {
            String fullCommand = ui.readCommand();
            parser.parse(fullCommand);
        }
        storage.storeFile(this.tasks);
    }


    public static void main(String[] args) throws IOException, EmptyInputException, NoResponseException {
        String path = "."+ File.separator+"data"+File.separator;
        String fileName = "duke.txt";
        new Duke(path , fileName).run();

    }
}
