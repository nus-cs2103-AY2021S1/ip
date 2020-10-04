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
    private static final String FILE_PATH = "data/Duke.txt";

    public Duke() throws IOException {
        checkFilePath();
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList(storage);
        this.parser = new Parser(taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponseForGui(String input) {
        String response = "";
        try {
            Ui ui = parser.parseUserInput(input);
            response = ui.getMessage();
        } catch (DukeException | IOException error) {
            response = Ui.makeErrorUi(error).getMessage();
        }
        return response;
    }

    protected static void checkFilePath() throws IOException {
        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        if (!Files.exists(Paths.get(FILE_PATH))) {
            Files.createFile(Paths.get(FILE_PATH));
        }
    }

    /**
     * Initiates and runs the main programme.
     */
    public void run() {

        Scanner sc = new Scanner(System.in);
        String input;
        Ui.makeStartUi().printMessage();
        input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Ui ui = parser.parseUserInput(input);
                ui.printMessage();
            } catch (DukeException | IOException error) {
                Ui.makeErrorUi(error).printMessage();
            }
            input = sc.nextLine();
        }
        Ui.makeEndUi().printMessage();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}



