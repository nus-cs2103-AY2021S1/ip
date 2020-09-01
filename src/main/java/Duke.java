import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javafx.scene.control.Label;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Wayne Tan
 * @since Aug 2020
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke construction specifying a filePath to initialise the Storage, Ui and TaskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } finally {
            ui.showWelcome();
        }
    }

    /**
     * Starts to run Duke by showing welcome. Uses Ui to scan input. Uses Parser to parse the input.
     * Execute Command. If the command is 'bye', loop terminates.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | ParseException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input. Replace this stub with
     * your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | ParseException e) {
            ui.showError(e.getMessage());
            return null;
        } catch (IOException e) {
            ui.showError(e.getMessage());
            return null;
        }
    }

    /**
     * Iteration 1: Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
