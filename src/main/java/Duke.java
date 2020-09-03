/**
 * Represents Duke program that executes the various commands given by the user.
 * A Duke object consists of a Storage object, which stores the tasks in the computer,
 * a TaskList object, which keeps track of the tasks, and a Ui object, which replies
 * the user accordingly.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    /**
     * Instantiates a Duke object and loads the tasks stored in the computer, if any.
     *
     * @param filePath The filePath of the file which stores the data.
     */
    public Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
//            ui.showLine();
            ui.showError(e.getMessage());
//            ui.showLine();

            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program by receiving the user commands and executing the corresponding
     * steps accordingly.
     */
//    public void run() {
//        ui.showWelcome();
//
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String input = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(input);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException ex) {
//                ui.showError(ex.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                isExit = c.isExit();
                return c.execute(tasks, ui, storage);
            } catch (DukeException ex) {
                return ui.showError(ex.getMessage());
            }
        }
        return "";
    }

//    public static String getGreeting() {
//        return ui.showWelcome();
//    }

    /**
     * Instantiates a Duke object and runs the program.
     *
     * @param args An array of strings.
     */
//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }
}
