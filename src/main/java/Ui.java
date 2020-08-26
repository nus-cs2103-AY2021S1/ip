import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    /**
     * Method to start the Chatbot and also read/save the history of tasks.
     * Contains all the logic that processes the incoming messages from the user.
     * @throws DukeException
     * @throws IOException
     */
    public void chat() throws DukeException, IOException {
        // Initialize Bot and print Welcome Message
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);

        // Get relative directory & init
        String dir = System.getProperty("user.dir");
        String textDir = dir + "/data/duke.txt";
        Storage file = new Storage(textDir);
        ArrayList<Task> arr = file.load();

        // Parse
        TaskList tList = new TaskList(arr);
        Parser input = new Parser(file, tList);
        input.parse();
    }


}
