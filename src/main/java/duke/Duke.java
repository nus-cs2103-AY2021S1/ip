package duke;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.utils.Constants;

import java.util.Objects;
import java.util.Scanner;


public class Duke {
    private static void launchDuke() {
        Storage database = new Storage("data/tasksTable.csv");
        Ui.loop(database);
    }
    public static void main(String[] args) {
        launchDuke();
    }

}

