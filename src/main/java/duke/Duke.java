package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Implements a version of Duke called Wyre
 *
 * @author Eryn Seo
 */
public class Duke {
    private Storage storage;
    private TaskList tList;
    private Ui ui;
    static boolean running = true;

    /**
     * Creates bot object while initializing necessary components / classes
     *
     * @param filePath relative path to the task list text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tList = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tList = new TaskList();
        } catch (IOException | ClassNotFoundException e) {
            ui.showUnexpectedLoadingError();
        }
    }

    /**
     * Runs the bot while there is user input & the user has not ended the session
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine() && running) {
            try {
                Parser p = new Parser(sc.nextLine(), ui, storage, tList);
                p.parseCommand();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showUnexpectedCommandMessage();
            } catch (IOException e) {
                ui.showUnexpectedSavingError();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("./task_list.txt").run();
    }
}