import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


public class Duke {
    public static Storage storage;
    public static TaskList taskList;
    public static Ui ui;
    public static boolean running;

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }
    
    public void run() {
        ui.welcome();
        this.running = true;
        while(running) {
            try {
                String userInput = ui.getUserInput();
                Parser.parse(userInput);
                this.storage.save();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
