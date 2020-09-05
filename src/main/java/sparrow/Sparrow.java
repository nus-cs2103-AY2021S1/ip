package sparrow;

import sparrow.commands.Command;
import sparrow.data.task.TaskList;
import sparrow.parser.Parser;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

import java.util.Scanner;

public class Sparrow {
    private Storage storage;
    private TaskList tasks;
    final private Ui ui;

    public Sparrow(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.loadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sparrow() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = storage.loadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.greet();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String command = sc.nextLine();
            Command c = parser.parseCommand(command);
            String result = c.execute(tasks, ui, storage);
            ui.replyToUser(result);
            isExit = c.getIsExit();
        }
    }

    public String getResponse(String userInput) {
        Parser parser = new Parser();
        Command c = parser.parseCommand(userInput);
        return c.execute(tasks, ui, storage);
    }

    public static void main(String[] args) {

        Sparrow sparrow = new Sparrow("Sparrow.txt");
        sparrow.run();
    }

}
