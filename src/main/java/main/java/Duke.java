package main.java;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a robot who can help the user to make todo list.
 * A <code>Duke</code> object is an instance of such robots.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * launch the Duke application, initialize the robot.
     */
    public void run() {
        ui.start();
        boolean flag = true;
        Parser parser = new Parser(tasks, true);
        while(flag) {
            String input = sc.nextLine();
            System.out.println(parser.handleCommand(input));
            flag = parser.getFlag();
        }
    }

    /**
     * Main method of the project, launch the project.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./command.txt").run();
    }
}

