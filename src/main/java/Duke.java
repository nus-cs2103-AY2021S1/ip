import java.nio.file.Paths;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Duke {
    private static final String DATABASE_DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"), "data").toString();
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises a new bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.DATABASE_DIRECTORY_PATH);
        this.parser = new Parser();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showErrorMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Returns the response of the bot depending on the user input.
     *
     * @return the response of the bot.
     */
    public String getResponse(String input) {
        assert input != null;

        try {
            String output = this.parser.parseCommands(this.tasks, this.ui, input);
            this.storage.save(this.tasks.getDatabase());
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
