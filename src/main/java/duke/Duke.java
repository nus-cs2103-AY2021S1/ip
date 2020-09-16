package duke;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;

import javax.swing.*;
import java.util.Scanner;

/**
 * Main method that runs input and output between system and user
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    protected Ui ui;

    /**
     * No-argument constructor for duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        this.tasks = new TaskList();
    }

    /**
     * constructor for Duke
     *
     * @param filePath String to determine the path of duke.txt file to write to
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
    }

    public String getResponse(String input) {
        Parser parser = new Parser(tasks, storage);
        if (input.equals("bye")) {
            JOptionPane.showMessageDialog(null, "Goodbye, hope to see you again!");
            Platform.exit();
        }
        return parser.parse(input);
    }

    /**
     * Driver method that handles input/output between user and system
     */
    public void run() {
        ui.greet();
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }
        ui.Bye();
    }

    /**
     * main method that runs Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
