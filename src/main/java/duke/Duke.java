package duke;

import duke.command.Command;
import duke.exception.*;

import java.io.File;
import java.util.Scanner;

public class Duke {

    private TaskList list;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (DukeInvalidDataException dukeInvalidData) {
            ui.showLoadingError();
            list = new TaskList();
        }
    }

    public void run() {
        ui.intro();
        boolean isEnd = false;
        while (!isEnd) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                isEnd = !c.execute(list, ui, storage);
            } catch (DukeEmptyIndexException |
                    DukeEmptyDescriptionException |
                    DukeEmptyByException |
                    DukeEmptyAtException e) {
                ui.showError(e);
            }
        }
    }

    public static void checkAndMakeDir(String filePath) {
        File f = new File(filePath);
        if (f.mkdir()) {
           System.out.printf("Created a directory '%s'%n", filePath);
        }
    }

    public static void main(String[] args) {
        String homePath = System.getProperty("user.home");
        checkAndMakeDir(homePath + "/data");
        Duke duke = new Duke(homePath + "/data/duke.txt");
        duke.run();
    }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
}
