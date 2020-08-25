import main.java.*;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * The Duke class is the main class in which the program is run.
 *
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Initializes a Duke object
     * @param filePath path in which the storage file should be written to
     */
    public Duke (String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage);
            ui = new Ui();
            parser = new Parser();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err){
            System.out.println("File not found in filepath provided");
        }
    }

    /**
     * Runs the program
     */
    public void run(){
        ui.welcomeMessage(taskList);
        ui.start();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String input = ui.readCommand();
                Command nextCommand = parser.interpret(input);
                nextCommand.execute(taskList, ui);
                isEnd = nextCommand.isEnd();
            } catch (IOException e) {
                ui.showError(e);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    };

    /**
     * The main function
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }
}
