package duke;

import duke.command.Command;
import duke.task.TaskList;


public class Duke {

    private Storage saveData;
    private TaskList list;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        this.saveData = new Storage(filePath);
        list = new TaskList(saveData.loadSavedData());
    }

    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(list, ui, saveData);
            isExit = c.isExit();
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
