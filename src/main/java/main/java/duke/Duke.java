package main.java.duke;

import java.io.*;

public class Duke {

    Storage storage;
    Ui ui;
    TaskList taskList;

    /**
     * Creates an instance of main.java.duke.Duke
     * @param filepath the directory of the tasklist.txt file where tasks are stored
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        // task
        taskList = new TaskList(storage.getStartupTaskList());
        ui = new Ui(taskList, storage);
    }

    /**
     * Starts the user interface
     */
    public void run() {
        ui.handleUserInput();
    }


    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        Duke application = new Duke(workingDir + File.separator
                + "tasklist.txt");
        application.run();
    }
}
