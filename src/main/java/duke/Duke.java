package duke;

import duke.commands.Command;
import duke.parser.Parser;

public class Duke {
    private Storage listStorage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String fileName) {
        this.listStorage = new Storage(fileName);
        this.taskList = new TaskList();
        this.ui = new Ui(this.listStorage, this.taskList);
    }

    public void run() {
        this.ui.printWelcome();
        this.listStorage.loadData(this.taskList);
        this.ui.loadFile();
        
        boolean isExit = false;
        while(!isExit) {
            String command = this.ui.getInput();
            Command c = Parser.parse(command);
            if (c!= null) {
                c.execute(this.ui, this.listStorage, this.taskList);
                isExit = c.canExit();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("dataFile.txt").run();
    }
}
