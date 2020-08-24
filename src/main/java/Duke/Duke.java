package Duke;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Duke.Commands.Command;
import Duke.Errors.DukeException;
import Duke.Helpers.Parser;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * This Duke class is the main class that prints out the relevant outputs by including all the subclasses of Task and
 * taking in the input.
 */
public class Duke {
    /**
     * todos includes all the string being input into the input.txt file.
     */
    private static List<String> todos = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    /**
     * This is a static function because it adds all the string in a line into the todos list is static, which contains information
     * of the action you want to do.
     */
   /* public void run() {
        boolean isExit = false;
        if(tasks.getAllTasks().size() == 0 || ui.getCurrNum() >= tasks.getAllTasks().size()){
            isExit = true;
        }
        if(!isExit) {
            ui.showWelcome();
            ui.showLine();
            int num = 0;
            while (!isExit && num < tasks.getAllTasks().size()) {
                num++;
                try {
                    String fullCommand = ui.readCommand();
                    ui.curr();
                    ui.showLine();// show the divider line ("_______")
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.showLine();
                }
            }
        }
    }*/
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("text-ui-test/input.txt");
        duke.run();
    }
}

