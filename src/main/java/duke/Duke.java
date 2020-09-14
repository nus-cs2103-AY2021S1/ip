package duke;

import java.nio.file.Path;

/**
 * Represents an interactive bot to handle tasks
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList lst = new TaskList();

    public Duke() {}
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public TaskList getLst() {
        return lst;
    }

    public void run() throws Exception {
        start();
        uiRun();
        end();
    }

    public void start() throws Exception {
        storage.writeToList(lst);
    }

    public void end() throws Exception {
        storage.writeToFile(lst);
    }

    public void uiRun() throws Exception {
        ui.run(lst);
    }

    public static void main(String[] args) throws Exception {
        java.nio.file.Path path = java.nio.file.Paths.get(System.getProperty("user.home"), "ip","start.txt");
        new Duke(path).run();
    }
}