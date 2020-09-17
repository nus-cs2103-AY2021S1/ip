package sparrow;

import java.util.Scanner;

import sparrow.commands.Command;
import sparrow.data.exceptions.IncorrectCommandException;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.parser.Parser;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class Sparrow {
    private Storage storage;
    private TaskList tasks;
    private VocabList vocabList;
    private final Ui ui;

    /**
     * Creates an instance of Sparrow, with data stored in the file specified by the path.
     * @param filePath Path to file for saving data.
     */
    public Sparrow(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.loadTaskListFromFile();
            vocabList = storage.loadVocabListFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an instance of Sparrow, with data stored at the default file path.
     */
    public Sparrow() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = storage.loadTaskListFromFile();
            vocabList = storage.loadVocabListFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Driver method of the app.
     */
    public void run() {
        ui.greet();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = sc.nextLine();
                Command c = parser.parseCommand(command);
                String result = c.execute(tasks, vocabList, ui, storage);
                ui.replyToUser(result);
                isExit = c.getIsExit();
            } catch (IncorrectCommandException ice) {
                ui.replyToUser(ice.getMessage());
            }
        }
    }

    /**
     * Generates a reply based on the user's input.
     * @param userInput User's text input.
     * @return Reply to user.
     */
    public String getResponse(String userInput) {
        try {
            Parser parser = new Parser();
            Command c = parser.parseCommand(userInput);
            return c.execute(tasks, vocabList, ui, storage);
        } catch (IncorrectCommandException ice) {
            return ice.getMessage();
        }
    }

    /**
     * Entry point into the application.
     */
    public static void main(String[] args) {

        Sparrow sparrow = new Sparrow("Sparrow.txt");
        sparrow.run();
    }

}
