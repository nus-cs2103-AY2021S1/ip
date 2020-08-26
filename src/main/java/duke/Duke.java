package duke;

import duke.Commands.AddInput;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

/**
 * Duke is the name of this program. It is an CLI app that reads and save
 * the user Tasks. The user can use it to keep tabs on their tasks and
 * and can mark them as done when they deem fit.
 */
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
    /**
     * Run the programme, using a Duke Object
     */
    public void run(){
        this.ui.startMessage();
        storage.loadTasks(this.tasks);
        AddInput.add_input(this.tasks, this.storage);
    }
}
