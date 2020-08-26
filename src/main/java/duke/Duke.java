package duke;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    public Duke() {
        storage = new Storage("data/tasks.txt", "data");
        taskList = storage.read();
        ui = new Ui(taskList);
    }

    public void run() {
        ui.displayGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String userCommand = scanner.nextLine();
                Command command = Parser.parse(userCommand);
                command.execute(storage, taskList, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.displayError(e.getMessage());
            }
        }
        scanner.close();
    }



    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }


}
