package duke;

import java.util.ArrayList;
import duke.io.Storage;
import duke.task.TaskList;
import duke.io.Ui;
import duke.io.Layout;
import duke.task.DukeException;


public class Duke {
    private Storage storage;
    private TaskList tasksData;
    private Ui ui;
    private Layout layout;

    public Duke(String filePath) {
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

    public void run() {
        ui.listen();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
