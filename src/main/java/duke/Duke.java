package duke;

import java.util.ArrayList;

import duke.commands.*;
import duke.util.*;

import javafx.scene.control.Label;

/**
 * Engages all the other classes to execute Duke.
 */
public class Duke {
    public static String filePath;
    private TaskList tasks;
    private OutputUi ui;
    private Storage storage;


    /**
     * Constructor.
     */
    public Duke() {
        ui = new OutputUi();
        storage = new Storage();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String getResponse(String input) throws DukeException {
        Command command = Parser.parse(input);
        return command.execute(tasks, ui, storage);
    }

}
