package duke.main;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a chatbot that allows crud operations, and can add three different types of task,
 * namely todo, events, and deadlines
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/tasks.txt");
        try {
            this.tasks = new TaskList(storage);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Run command to start Duke
     *
     * @param sc Scanner for input
     */
    public void run(Scanner sc) {
        this.ui.showWelcome();
        this.ui.checkCommands(sc, this.tasks);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new Duke("./data/tasks.txt").run(sc);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}

/**
 * The main class for running Duke, the ChatBot.
 */

//public class Duke extends Application {

//    private final Storage storage;
//    private final TaskList tasks;
//    private final Ui ui;
//
//    /**
//     * Initialisation for Duke, the ChatBot.
//     *
//     * @param filepath Requires a filepath which will be relative to where Duke was ran, to save and load files from
//     */
//    public Duke(String filepath) {
//        ui = new Ui();
//        storage = new Storage(filepath);
//        tasks = storage.loadData(ui);
//    }
//
//    public Duke() {
//        ui = new Ui();
//        storage = new Storage("data/tasks.txt");
//        tasks = storage.loadData(ui);
//    }

//    /**
//     * Runs Duke, the ChatBot.
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeError e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
//}
