import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = TaskList.generateTaskList(storage);
    }

    public void run() {
        ui.start(taskList);
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
