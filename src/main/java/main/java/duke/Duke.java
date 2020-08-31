package main.java.duke;

import java.io.File;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * An empty constructor, known as the init method for the method start to
     * launch the Graphic User Interface
     */
    public Duke() {

    }

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
     * Starts the user interface for command line interface
     */
    public void run() {
        ui.handleUserInput();
    }

    /**
     * Passes input string to ui.getResultFromParser(String input) and returns the response to be displayed in GUI.
     * @param input user input
     * @return duke's response to be displayed in GUI.
     */
    protected String getResponse(String input) {
        return "Duke responds:\n" + ui.getResultFromParser(input);
    }

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        Duke application = new Duke(workingDir + File.separator
                + "tasklist.txt");
        application.run();
    }
}
