import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import java.time.format.DateTimeParseException;



public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.read());
        } catch (DukeException | IOException ex) {
            ui.showLoadingError();
            tasks = new TaskList(tasks.getTasks());
        }
    }

    public void run() {
        ui.printGreetings();
        while (ui.hasMoreInput()) {
           try {
               String userInput = ui.readCommand();
               Command command = Parser.parseCommands(userInput);
               command.execute(this.tasks, this.storage, this.ui);
           } catch (DukeException | IOException ex) {
               System.out.println(ex.getMessage());
           }
           finally {
               System.out.println(Ui.line);
           }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }



}




