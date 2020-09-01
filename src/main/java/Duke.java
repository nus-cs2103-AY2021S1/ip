import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import dukeclass.Parser;
import dukeclass.Storage;
import dukeclass.TaskList;
import dukeclass.Ui;

/**
 * Encapsulates a chat robot that you can chat with to set tasks for yourself.
 */
public class Duke {

    /**
     * Duke to be started, create files for storage, run commands and write to files.
     */
    public static void startDuke() {
        TaskList taskList = new TaskList();

        File f = Storage.createFile();

        System.out.println(Ui.welcomeMessage());

        try {
            Storage.readFromFile(f, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Loading done");


        try {
            Parser.parseCommands(taskList);

        } catch (Exception e) {
            System.out.println(Ui.unknownInputErrorMessage(e));
        }


        try {
            Storage.writeToFile(f, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println(Ui.endMessage());


    }

    /**
     * Main method to start Duke
     */
    public static void main(String[] args) throws FileNotFoundException {

        Duke.startDuke();

    }
}
