import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The Duke program keeps track of the list of tasks to be done.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/duke2.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke1.png"));

//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println(logo +"Hello! I'm Duke\n" + "What can I do for you?");
//
//        IOHandler handler = new IOHandler();
//
//        handler.handleIO();
//
//        System.out.print("Bye. Hope to see you again soon!");
//    }



    public Duke() throws IOException {

        String fileName = "data/duke.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        List<String> files = FileHandler.readSavedFile(fileName);
        for (String value : files) {
            Task task = TextAndTaskConverter.textConverter(value);
            taskManager.getTasksList().add(task);
        }
    }

    IOHandler handler = new IOHandler();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        //FileHandler.writeToFile(this.fileName, this.taskManager);

        return IOHandler.handleIO(input);
    }
}
