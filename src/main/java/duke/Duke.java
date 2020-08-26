package duke;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        tasks.loadFromStorage(storage);
    }
    
    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(ui.readNextCommand());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
