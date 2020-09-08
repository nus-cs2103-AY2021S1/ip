import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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


    private TaskManager taskManager;
    private FileHandler fileHandler;

    public Duke(String filePath) throws IOException {
        this.fileHandler = new FileHandler(filePath);
        this.taskManager = new TaskManager();

        try {
            List<String> files = FileHandler.readSavedFile(filePath);
            for (String value : files) {
                Task task = TextAndTaskConverter.textConverter(value);
                taskManager.getTasksList().add(task);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo +"Hello! I'm Duke\n" + "What can I do for you?");
        Duke duke = new Duke("data/duke.txt");
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        IOHandler ioHandler = Parser.parseInput(input);
        System.out.println(ioHandler.handleIO(input, taskManager, fileHandler));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        IOHandler ioHandler = Parser.parseInput(input);
        return ioHandler.handleIO(input, taskManager, fileHandler);
    }
}
