package duke;

import java.util.ArrayList;

import duke.command.Command;
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
        String dukeRespond;
        try {
            Command dukeCommand = tasksData.getCommands(input.split(" "));
            dukeRespond = dukeCommand.execute(tasksData.getTasks(), layout);
        } catch (DukeException e) {
            dukeRespond = layout.print(e.getMessage());
        }
        return "Duke heard: " + dukeRespond;
    }
    
    protected String greet() {
        return ui.greet();
    }

    public Duke() {
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        layout = new Layout();
        try {
            tasksData = new TaskList(storage.load(), storage);
        } catch (Exception e) {
            DukeException d = new DukeException(" Unable to load tasks from hard disk");
            layout.print(d.getMessage());
            tasksData = new TaskList(new ArrayList<>(), storage);
            
        } finally {
            ui = new Ui(tasksData);
        }
        
    }
}
