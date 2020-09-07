import java.nio.file.Paths;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Duke {
    private static final String DATABASE_DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"),
            "../../../", "data").toString();
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     */
    public Duke() {
        super();
    }

    /**
     */
    public Duke(String databaseDirectoryPath) {
        this.ui = new Ui();
        this.storage = new Storage(databaseDirectoryPath);
        this.parser = new Parser();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage("Could not load saved tasks");
            this.tasks = new TaskList();
        }
    }
//
//    /**
//     * Starts the bot.
//     */
//    public void run() {
//        this.ui.greetUser();
//
//        boolean keepApplicationRunning = true;
//        Scanner sc = new Scanner(System.in);
//
//        while (keepApplicationRunning) {
//            try {
//                String userInput = this.ui.getUserInput(sc);
//                if (userInput.equals("bye")) {
//                    keepApplicationRunning = false;
//                }
//                this.parser.parseCommands(this.tasks, userInput, sc);
//                // Saves the state after each command instead of only saving to the database upon exit
//                this.storage.save(this.tasks.getDatabase());
//            } catch (DukeException e) {
//                this.ui.showErrorMessage(e.getMessage());
//            }
//        }
//
//        this.ui.showExitMessage();
//        System.exit(0);
//    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
