import java.util.Scanner;
import java.io.IOException;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;



public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        File file = new File(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.greet();
        while (ui.getIn().hasNextLine()) {
            String command = ui.getUserCommand();
            Parser.respond(command, ui, tasks, storage.filePath);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

