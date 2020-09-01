package duke;

import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object
     */
    public Duke(){}

    /**
     * Constructs a Duke object
     * @param filePath the file path
     * @throws FileNotFoundException
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(TaskList.readTextFile2List(storage.load()));
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Overrides the start method
     * @param stage stage
     */
    @Override
    public void start(Stage stage){
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs the Duke app
     * @throws IOException exception for reading files
     */
    public void run() throws IOException {
        ui.showWelcomeMessage();
        String inputCommand;
        Scanner sc = new Scanner(System.in);
        int condition = 1;
        while (condition == 1) {
            inputCommand = sc.nextLine();
            condition = parser.parse(inputCommand, tasks);
        }
        Checker.checkAndPrint(tasks);
        storage.writeFile(tasks);
    }

    /**
     * Drives the Duke app
     * @param args parameter for main method
     * @throws IOException exception for reading files
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/main.java.duke.txt").run();
    }
}
