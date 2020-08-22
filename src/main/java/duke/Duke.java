package duke;

import duke.commands.EnumCommand;
import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Scanner;


public class Duke {
    static final String filePath = "duke.txt";
    private Storage storage;
    private TaskList result;
    private Ui ui;

    public static void main(String[] args) {
        new Duke(filePath).run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
                Command.executeCommand(enumCommand, instruction, result);
                storage.storeToFile(result);
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }


}
