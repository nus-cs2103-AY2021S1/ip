package duke;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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

    /**
     * Delay the exit of duke for user to see the Duke's response for 2 seconds
     */
    //Solution below adapted from https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
    public void delayExit() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
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
