package main.java.duke;

import java.io.*;

public class Duke {

    Storage storage;
    Ui ui;
    TaskList taskList;

    /**
     * Creates an instance of main.java.duke.Duke
     * @param filepath the directory of main.java.duke.Duke application storage
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        // task
        taskList = new TaskList(storage.getStartupTaskList());
        ui = new Ui(taskList, storage);
    }

    /**
     * Interacts with TaskList, Ui, Storage and Parser to facilitate the
     * duke application
     */
    public void run() {
        ui.handleUserInput();
    }


    // Note that all the outputs are formatted with two spaces before.
    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        Duke application = new Duke(workingDir + File.separator
                + "tasklist.txt");
        application.run();
    }
}
