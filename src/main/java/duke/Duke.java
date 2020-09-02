package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.command.Command;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;


public class Duke {
    private final Ui ui;
    private final DukeFileHandler fileHandler;
    private TaskList tasks;


    Duke(String path) {
        ui = new Ui();

        fileHandler = new DukeFileHandler(path);

    }


    public void showWelcome() {
        ui.welcome();
        try {
            tasks = new TaskList(fileHandler.readFile());
            if (!tasks.isNull()) {
                ui.displayList(tasks.getList(), "Here are your current tasks:");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(new ArrayList<>());
        }
    }


    private void enteredInput(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, fileHandler);


        } catch (DukeException ex) {
            ui.displayThis(ex.getMessage());
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        enteredInput(input);

        return "This is left empty";
    }


}
