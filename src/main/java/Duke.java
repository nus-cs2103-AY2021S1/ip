import java.io.File;
import java.awt.event.AdjustmentEvent;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exceptions.*;
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
            ui.curr = tasks.allTasks.get(0);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * This is a static function because it adds all the string in a line into the todos list is static, which contains information
     * of the action you want to do.
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        if(tasks.allTasks.size() == 0 || ui.currNum >= tasks.allTasks.size()){
            isExit = true;
        }
        while (!isExit) {
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
    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args)  {
       new Duke("input.txt").run();
    }
}

