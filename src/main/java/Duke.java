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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * change access modifier to private after packaging it
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            this.endDuke();
            return Ui.endMessage();
        } else{
            try {
                return Parser.parseCommands(taskList, input);

            } catch (Exception e) {
                return Ui.unknownInputErrorMessage(e);
            }
        }
    }

    public void endDuke() {
        try {
            Storage.writeToFile(f, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Duke to be started, create files for storage, run commands and write to files.
     */
    public static void startDuke() {

        System.out.println(Ui.welcomeMessage());

        System.out.println("Loading done");

    }

    /**
     * Main method to start Duke
     */
    public static void main(String[] args) throws FileNotFoundException {

        Duke.startDuke();

    }


}

