package duke;

import duke.Commands.AddInput;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

public class Duke {

    private final static String directory =  System.getProperty("user.dir");
    private Storage storage;
    private static TaskList tasks;
    private Ui ui;


    public Duke(){
        storage = new Storage(this.directory);
        ui = new Ui();
        this.tasks = new TaskList();

    }

    public void run(){
        this.ui.startMessage();
        storage.loadTasks(this.tasks);
        AddInput.add_input(this.tasks, this.storage);
    }
}
