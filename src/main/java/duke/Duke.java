package duke;

import duke.task.TaskList;
import duke.utils.Storage;

public class Duke {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Ui ui = new Ui(list);
        Storage storage = new Storage(list);
        storage.readSavedFile();
        ui.run();
        storage.saveDataToFile();
    }
}
