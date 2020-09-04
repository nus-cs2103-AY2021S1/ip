package duke;

import duke.storage.Storage;
import duke.ui.Cli;;

public class Duke {
    public static void launchDuke() {
        Storage database = new Storage("data/tasksTable.csv");
        Cli.loop(database);
    }

}

