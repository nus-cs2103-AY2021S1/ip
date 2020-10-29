package duke;

import duke.Gui.Gui;
import duke.command.Command;
import duke.component.DukeException;
import duke.component.Parser;
import duke.component.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Duke {
    private static final Path filepath = Paths.get(".", "data", "duke.txt");
    private Storage storage;
    private TaskList taskList;
    private boolean isExtracted = true;

    /**
     * Creates an instance of Duke and ui. Initialises TaskList.
     * Initialises empty list of tasks if file lookup is unsuccessful.
     */
    public Duke() {
        assert filepath instanceof Path : "Filepath for data file must be defined";
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.getListOfTasks());
        } catch (DukeException e) {
            isExtracted = false;
            taskList = new TaskList();
        }
    }

    /**
     * Executes logic in this class
     * @param args default style for this method
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Application.launch(Main.class, args);
    }

    /**
     * Greets user with welcome message
     * @return String of message to the duke.Gui application
     */
    public String getGreeting() {
        String response = "Hello and welcome to the GeNiaaz personal assistant";
        if (!this.isExtracted) {
            return response + "\n\nData was not extracted successfully, new list created";
        }
        return response;
    }

    /**
     * Gets string of response to be shown to user
     * @param input command given by user
     * @return string of response to show user
     */
    public String getResponse(String input) {
        Gui gui = new Gui();
        ArrayList<String> responseList = new ArrayList<>();
        String response = "";
        try {
            Command c = Parser.parse(input);
            // exit when bye command issue
            if (c.isExit()) {
                JOptionPane.showMessageDialog(null, "Goodbye, hope to see you again!");
                Platform.exit();
            }
            responseList = c.execute(taskList, gui, storage, responseList);
        } catch (DukeException e) {
            responseList.add(e.getMessage());
        }
        for (String s : responseList) {
            response = response + ("\n") + (s);
        }
        return response;
    }
}
