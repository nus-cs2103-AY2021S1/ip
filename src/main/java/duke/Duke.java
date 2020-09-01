package duke;

import java.util.ArrayList;
import duke.task.TaskList;
import duke.task.DukeException;
import duke.io.Ui;
import duke.io.Layout;
import duke.io.Storage;

/**
 * Represents a task manager, Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasksData;
    private Ui ui;
    private Layout layout;
    
    protected String getResponse(String input) {
        String dukeRespond = tasksData.readCommands(input.split(" "));
        return "Duke heard: " + dukeRespond;
    }
    
    protected String greet() {
        return ui.greet();
    }

    public Duke() {
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        try {
            tasksData = new TaskList(storage.load(), storage);
            ui = new Ui(tasksData);
        } catch (Exception e) {
            layout = new Layout();
            DukeException d = new DukeException(" Unable to load tasks from hard disk");
            layout.print(d.getMessage());
            tasksData = new TaskList(new ArrayList<>(), storage);
        }
    }
}
