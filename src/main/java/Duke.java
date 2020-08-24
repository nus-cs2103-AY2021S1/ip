import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot.
 * It keeps track of various tasks to be done.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
        this.parser = new Parser(taskList);
        this.ui = new Ui();
    }

    /**
     * Initiates and runs the main programme.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        ui.start();
        input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                parser.parseUserInput(input);
            } catch (DukeException | IOException e) {
                ui.showError(e);
            }
            input = sc.nextLine();
        }
        ui.end();
    }

    public static void main(String[] args) throws IOException, DukeException {

        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        if (!Files.exists(Paths.get("data/Duke.txt"))) {
            Files.createFile(Paths.get("data/Duke.txt"));
        }

        new Duke("data/Duke.txt").run();

    }

}



