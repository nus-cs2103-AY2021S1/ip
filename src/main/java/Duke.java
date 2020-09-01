import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import commands.Command;
import exceptions.DukeException;
import exceptions.DukeIoException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tasks.TaskList;
import utils.*;
import utils.ui.CLI;
import utils.ui.GUI;
import utils.ui.UI;

/**
 * The main driver Class for the Duke Application.
 */
public class Duke {

    private static ResourceBundle strings;
    private static UI ui;
    private static TaskList tasks;
    private static Storage storage;

    private static void initializeDuke() throws DukeIoException {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("StringsBundle", Locale.ENGLISH);
        storage = Storage.createStorage("database", "tasks.ser");
        tasks = new TaskList(storage.load());
        tasks.setObserver(storage);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ui = new CLI();

        try {
            initializeDuke();
        } catch (DukeIoException e) {
            ui.print(e.getMessage());
        }
        ui.print(strings.getString("output.greeting"));

        String input;
        String mainCommand = "";
        String output;
        HashMap<String, String> parameters;

        while (!mainCommand.equals(strings.getString("command.bye"))) {
            input = ui.read();
            mainCommand = Parser.parseMainCommand(input);
            parameters = Parser.parseParameters(input);

            try {
                output = Command.createCommand(mainCommand)
                        .execute(parameters, tasks);
                ui.print(output);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }

        }
    }

    public Duke() {
        ui = new GUI();
        try {
            initializeDuke();
        } catch (DukeIoException e) {
            ui.print(e.getMessage());
        }
    }

    public String getResponse(String input) {
        String mainCommand = Parser.parseMainCommand(input);
        HashMap<String, String> parameters = Parser.parseParameters(input);

        try {
            System.out.println(mainCommand);
            return Command.createCommand(mainCommand)
                    .execute(parameters, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
