package gel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * gel.Gel is an task planner. A <code>gel.Gel</code> object is a bot which
 * enables user to plan their tasks.
 */
public class Gel {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * Constructor for class gel.Gel.
     *
     * @param filePath takes in the filepath of the storage file.
     */
    public Gel (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            storage.checkFileExistence();
            taskList = storage.load(ui);
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(ui);
        }
    }

    /**
     * Starts the task planner <code>gel.Gel</code> and interact with user.
     */
    public void run() {
        // initialise list and scanner
        try {
            Scanner sc = new Scanner(System.in);
            ui.showWelcomeMessage();
            Parser.parseUserInput(sc, storage, ui, taskList);
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println();
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        new Gel("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            return Parser.parseUserInputFromGui(storage, ui, taskList, input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMsg() {
        return ui.showWelcomeMessage();
    }
}
