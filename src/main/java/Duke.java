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
        this.ui = new Ui();
        this.storage = new Storage(Duke.DATABASE_DIRECTORY_PATH);
        this.parser = new Parser();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage("Could not load saved tasks");
            this.tasks = new TaskList();
        }
    }

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
        try {
            String output = this.parser.parseCommands(this.tasks, input);
            this.storage.save(this.tasks.getDatabase());
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
