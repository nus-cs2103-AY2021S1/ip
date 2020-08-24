import main.java.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    final static String UNDERSCORE = "____________________________________________________________ \n";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Duke (String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage);
            ui = new Ui();
            parser = new Parser();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err){
            System.out.println("File not found in filepath provided");
        }
    }

    public void run(){
        ui.welcomeMessage(taskList);
        ui.start();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String input = ui.readCommand();
                Command nextCommand = parser.interpret(input);
                nextCommand.execute(taskList, ui);
                isEnd = nextCommand.isEnd();
            } catch (IOException e) {
                ui.showError(e);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    };

    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }
}
