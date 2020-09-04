package duke;

/**
 * Presents Duke class with "Ka To"
 * Interacts with the user and answers user commands
 * Manages the task list of the user
 * Follows the coding standard
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        if (this.storage.load().isEmpty()) {
            this.tasks = new TaskList();
        }else{
            this.tasks = new TaskList(storage.load());
        }
    }

    //run the ui and parser
    private void run() {
        this.ui.printGreet();
        while (this.ui.input.hasNextLine()) {
            String command = this.ui.getInput();
            Parser.processCommand(command, this.ui, this.tasks, this.storage.filePath);
        }
    }


    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke manager = new Duke(filePath);
        manager.run();
    }

}
