import Duke.Tool.*;
import Duke.Task.*;

public class Duke {
    
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Command command;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        command = new Command();
        taskList = new TaskList(storage.load());
    }

    //store user input and respond to different input
    public void run() {
        this.ui.printWelcomeMessage();
        parser.parse(storage, ui, taskList, command);
        this.ui.sendBye();
    }

    //run bot
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
