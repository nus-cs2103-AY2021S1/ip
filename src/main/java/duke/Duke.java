package duke;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.TaskList;
import javafx.application.Platform;

import javax.swing.*;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    protected Ui ui;

    public Duke() {
        String filePath = getPathName();

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

    // Credit to nicholas-gcc and Ziyang-98 for directory issues
    private String getPathName() {
        boolean ifPathDirExists = System.getProperty("user.dir").endsWith("CS2103 IP");
        return ifPathDirExists
                ? "data/duke.txt"
                // Creates a save file on the user's home directory if user is not in ip directory
                : System.getProperty("user.dir") + "/duke.txt";
    }

    /**
     * Driver method that handles input/output between user and system
     */
    public void start() {
        ui.chat();
        Parser parser = new Parser(tasks, storage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }
        ui.exit();
    }

    /**
     * main method that runs Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().start();
    }
}
