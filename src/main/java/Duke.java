import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    String filePath;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initialise Duke with the filePath.
     */
    public Duke() {
        Ui ui = new Ui();
        Storage storage = new Storage("./duke.txt");
        TaskList tasks = storage.readFile();
        this.filePath = "./duke.txt";
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Runs the Duke by initialise the Parser.
     */
    public void run() {
        Parser parser = new Parser(ui);
        parser.parser(tasks, filePath);
    }


    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String getResponse(String input){
        Parser parser = new Parser(this.ui);
        String output = parser.parser(input, this.tasks);
        return "Duke: " + "\n" + output;
    }

    public static void main(String[] args){
        new Duke().run();
    }


}
