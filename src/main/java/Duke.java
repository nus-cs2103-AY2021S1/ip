import java.io.*;
import java.util.Scanner;

/**
 * Represents Duke bot and contains main information of how it works.
 */
public class Duke {
    private static final String PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }
    
    /**
     * Creates Duke object.
     * @param filePath Pathname of the file that stores tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Reads user input then acts accordingly and stores user data.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            parser.parse(sc.nextLine(), tasks, ui, storage);
        }
    }

    /**
     * Scans and executes user inputs.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}