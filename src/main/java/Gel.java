import gel.Parser;
import gel.Storage;
import gel.TaskList;
import gel.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Gel {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Gel (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = storage.load(ui);
        } catch (FileNotFoundException e) {
            taskList = new TaskList(ui);
        }
    }

    public void run() {
        // initialise list and scanner
        try {
            storage.checkFileExistence();
            Scanner sc = new Scanner(System.in);
            ui.showWelcomeMessage();
            Parser.parseUserInput(sc, storage, ui, taskList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        new Gel("data/tasks.txt").run();
    }
}