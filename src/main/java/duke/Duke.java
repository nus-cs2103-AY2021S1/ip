package duke;

import java.text.ParseException;

import java.io.*;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() throws IOException {
        this.ui.uiRun(this.tasks, this.storage);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("/Users/viromics/Desktop/CS2103/ip/ip/src/main/java/duke/resources/todo.txt").run();
    }
}
