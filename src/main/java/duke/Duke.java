package duke;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.TaskList;
import javafx.application.Platform;

import javax.swing.*;
import java.io.IOException;
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
        boolean PathDirExists = System.getProperty("user.dir").endsWith("CS2103 IP");
        if (PathDirExists) {
            return "data/duke.txt";
        } else {
            String filePath = System.getProperty("user.dir") + "/duke.txt";
            return filePath;
        }
    }


    /**
     * Driver method that handles input/output between user and system
     */
    public void start() {
        try {
            // ui.chat();
            Parser parser = new Parser(tasks, storage);
            parser.load(storage.getFilePath());
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                parser.parse(input);
                input = sc.nextLine();
            }
            ui.exit();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * main method that runs Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke run = new Duke();
        run.start();
    }
}
