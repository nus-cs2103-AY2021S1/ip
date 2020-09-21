import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dukeclass.Parser;
import dukeclass.Storage;
import dukeclass.TaskList;
import dukeclass.Ui;
import javafx.application.Platform;

/**
 * Encapsulates a chat robot that you can chat with to set tasks for yourself.
 */
public class Duke {
    private TaskList taskList;
    private File f;

    Duke() {
        this.taskList = new TaskList();
        this.f = Storage.createFile();

        try {
            Storage.readFromFile(f, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public String getResponse(String input) {
        try {
            return Parser.parseCommands(this.taskList, input);

        } catch (Exception e) {
            return Ui.unknownInputErrorMessage(e);
        }

    }

    public void endDuke() {
        try {
            Storage.writeToFile(f, taskList);
            System.out.print("write success");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Duke to be started, create files for storage, run commands and write to files.
     */
    public static void startDuke() {

        System.out.println(Ui.welcomeMessage());

    }

    /**
     * Main method to start Duke
     */
    public static void main(String[] args) throws FileNotFoundException {

        Duke d = new Duke();
        Duke.startDuke();
        d.endDuke();


    }


}

