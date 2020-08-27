package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            ui.greetingMessage();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean running = true;
        while (running) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.store(tasks);
                if(c.isExit()) { running = false; }
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String filePathString = ".\\data\\duke.txt";
            File taskData = new File(filePathString);
            if (taskData.exists()) {
                //nothing.
            } else {
                File dir = new File(".\\data");
                System.out.println(dir.mkdir());
                boolean created = taskData.createNewFile();
                assert created;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Duke("./data/duke.txt").run();
    }
}
