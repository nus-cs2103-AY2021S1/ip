import commands.EnumCommand;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.*;
import ui.Ui;

import java.util.Scanner;

import static commands.Command.executeCommand;

public class Duke {
    static final String dirPath = "..\\data";
    static final String filePath = "../data/duke.txt";
    private Storage storage;
    private TaskList result;
    private Ui ui;

    public static void main(String[] args) {
        new Duke(filePath).run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, dirPath);
        try {
            result = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            result = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            try {
                EnumCommand enumCommand = Parser.parseCommand(instruction);
                executeCommand(enumCommand, instruction, result);
                storage.storeToFile(result);
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }


}
