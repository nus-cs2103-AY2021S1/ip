package duke;

import duke.storage.Storage;
import duke.ui.Cli;;

public class Duke{


    private static void launchDuke() {
        Storage database = new Storage("data/tasksTable.csv");
        Cli.loop(database);
    }
    public static void main(String[] args) {
        launchDuke();
    }

}

