package duke;

import java.io.FileNotFoundException;

import duke.exception.DukeException;
import duke.tasks.Task;

/**
 * The Duke class is the main class in which the program is run.
 *
 */
public class Duke {
    private Storage storage = new Storage("./src/main/java/data/duke.txt");
    private Ui ui = new Ui();
    private TaskList taskList;
    private Parser parser = new Parser();

//    /**
//     * Initializes a Duke object
//     * @param filePath path in which the storage file should be written to
//     */

//    public Duke (String filePath) {
//        storage = new Storage(filePath);
//        try {
//            taskList = new TaskList(storage);
//        } catch (DukeException err) {
//            System.out.println(err.getMessage());
//        } catch (FileNotFoundException err) {
//            System.out.println("File not found in filepath provided");
//        }
//    }
    /**
     * Initializes a Duke object
     */
    public Duke() {
        try {
            taskList = new TaskList(storage);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err) {
            System.out.println("File not found in filepath provided");
        }
    }
//    /**
//     * Runs the program
//     */
//    public void run() {
//        ui.welcomeMessage(taskList);
//        ui.start();
//        boolean isEnd = false;
//        while (!isEnd) {
//            try {
//                String input = ui.readCommand();
//                Command nextCommand = parser.interpret(input);
//                nextCommand.execute(taskList, ui);
//                isEnd = nextCommand.isEnd();
//            } catch (DukeException e) {
//                ui.showError(e);
//            }
//        }
//    };
//    /**
//     * The main function
//     * @param args
//     */
//    public static void main(String[] args) {
//        new Duke().run();
//    }

    public String getResponse(String input) throws DukeException {
        Command nextCommand = parser.interpret(input);
        return nextCommand.execute(taskList, ui);
    }
}
