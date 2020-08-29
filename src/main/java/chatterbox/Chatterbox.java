package chatterbox;

import java.io.IOException;
import java.util.Scanner;

import chatterbox.task.Task;
import chatterbox.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Handles the main loop and the high level logic flow of the program.
 */
public class Chatterbox extends Application {
    private final TaskList tasks;
    private final Scanner scanner;

    /**
     * Initializes the task list and load stored tasks.
     */
    public Chatterbox() {
        Storage store = new Storage();
        tasks = new TaskList(store);
        tasks.loadTasks();
        scanner = new Scanner(System.in);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows the welcome message, then processes each line of user input until "bye" is typed.
     */
    public void run() {
        Ui.showWelcomeMessage();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (ChatterboxException | IOException e) {
                Ui.showErrorMessage(e.toString());
            }
            input = scanner.nextLine();
        }
        Ui.showFarewellMessage();
    }

    /**
     * Processes user input and executes actions based on them.
     *
     * @param input Raw user input.
     * @throws ChatterboxException If input string is empty or invalid command is given.
     * @throws IOException         If data cannot be read/written from the save file.
     */
    private void processInput(String input) throws ChatterboxException, IOException {
        // Check if input is just whitespace
        if (input.strip().equals("")) {
            throw new ChatterboxException("Input cannot be empty.");
        }

        // Get first word of input
        String command = (input + " ").split(" ")[0].toLowerCase();

        // Process command
        if (command.equals("list")) {
            tasks.printAllTasks();
        } else if (command.equals("done") || command.equals("delete")) {
            // Get the task number after the command, check if it is valid
            int taskNo;
            try {
                taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new ChatterboxException("Please enter a number after the command.");
            }

            // Mark as done or delete based on the command
            if (command.equals("done")) {
                tasks.setTaskAsDone(taskNo);
            } else {
                tasks.deleteTask(taskNo);
            }
        } else if (command.equals("find")) {
            tasks.findAndPrintTasks(input.split(" ", 2)[1]);
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            Task t = Parser.parseTask(input);
            tasks.addTask(t);
        } else {
            throw new ChatterboxException("That's not a valid command.");
        }
    }
}

