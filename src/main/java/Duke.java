import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.File;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        String filePath = "./data";
        String fileName = "data.txt";
        this.ui = new Ui();
        this.storage = new Storage(filePath + "/" + fileName);
        try {
            File dir = new File(filePath);
            File file = new File(filePath, fileName);
            if (dir.exists() && file.exists()) {
                this.taskList = new TaskList(storage.load());
            } else if (dir.exists()) {
                // case where only folder exist
                createFile();
            } else {
                // case where folder does not exist
                createDirectory(dir);
                createFile();
            }
        } catch (DukeException e) {
            Ui.printException(e);
            System.exit(1);
        }
    }

    private void createDirectory(File dir) {
        dir.mkdir();
    }

    private void createFile() throws DukeException {
        try {
            storage.createFile();
            this.taskList = new TaskList();
        } catch (IOException e) {
            throw new DukeException("Error Creating File");
        }
    }


    // TODO: Print exception should somewhere be in getResponse
    String getResponse(String input) {
        Command command;
        try {
            if (Parser.isBye(input)) {
                this.storage.save(this.taskList);
                System.exit(0);
            }
            command = Parser.parseInput(input, storage, taskList);
            return command.execute();
        } catch (DukeException e) {
            return Ui.printException(e);
        }
//        return this.execute(command);
    }

//    private void run() {
//        runLoopUntilExit();
//        exit();
//    }

//    private void runLoopUntilExit() {
//        Command command;
//        do {
//            String input = ui.getUserInput();
//            command = Parser.parse(input);
//            this.execute(command);
//        } while (command.getCommandType() != Command.EXIT);
//    }

//    private void exit() {
//        ui.showGoodbyeMessage();
//        System.exit(0);
//    }

    public static void main(String[] args) {
        new Duke();
    }
}
