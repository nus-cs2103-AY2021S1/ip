package duke;

import java.util.Scanner;
import java.nio.file.Path;

/**
 * Represents a Duke app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates an instance of a Duke app.
     * @param filePath Location that tasks are to be stored in.
     */
    public Duke(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    /**
     * Runs the Duke app.
     */
    public void run() {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            parser.parse(userInput);
            switch(parser.getSplitUserInput()[0].toLowerCase()) {
                case "dismiss":
                    sc.close();
                    Ui.dismiss();
                    break;
                case "scroll":
                    tasks.printAllTasks();
                    break;
                case "conquer":
                    int conquerIndex = tasks.conquerTask(parser.getSplitUserInput());
                    storage.overwriteInFile(conquerIndex);
                    break;
                case "delete":
                    int deleteIndex = tasks.deleteTask(parser.getSplitUserInput());
                    storage.removeFromFile(deleteIndex);
                    break;
                case "assist":
                    Ui.assist();
                    break;
                case "find":
                    tasks.returnSearchedTask(parser.getSplitUserInput());
                    break;
                default:
                    Task t = tasks.addTask(parser.getSanitisedUserInput());
                    storage.writeToFile(t, parser.getSanitisedUserInput());
            }
        }
    }

    /**
     * Creates and runs an instance of a Duke app.
     * @param args 
     */
    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
        new Duke(path).run();
    }
}
