import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        boolean continueOperate = true;
        while (continueOperate) {
            String order = ui.getOrder();
            Task c = Parser.parse(order, tasks);
            c.excute(tasks, ui, storage);
            continueOperate = !c.isExit;
        }
        ui.showGoodbye();
    }

        public static void main(String[] args) {
            new Duke("data/tasks.txt").run();
        }


}
