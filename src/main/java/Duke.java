import java.io.File;
import java.util.Scanner;

import Command.Command;
import DukeComponent.Parser;
import DukeComponent.Ui;
import TaskList.TaskList;
import TaskList.Storage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Duke asks user for their todos and makes a todo list.
 * Tasks can be viewed in a list, marked as done and deleted.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * The default constructor for a Duke object.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage);
        parser = new Parser();
    }

    /**
     * This constructor takes in the file to save data.
     */
    public Duke(File file) {
        storage = new Storage(file);
        tasks = new TaskList(storage);
        parser = new Parser();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    /**
     * This method collects commands from user until an exit command 'bye' is read.
     * Valid commands will be executed.
     */
    void run() {
        Scanner scanner = new Scanner(System.in);
        String s;
        Ui.welcomeMessage();
        Command command;
        while ((command = Parser.parse(s = scanner.nextLine(), tasks.size())).getType()
                != Command.CommandType.EXIT) {
            command.act(tasks);
        }
        command.act(tasks);
    }

    /**
     * This method is the main method.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
