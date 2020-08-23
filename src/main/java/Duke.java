import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String directoryPath, String filePath) {
        ui = new UI();
        storage = new Storage(directoryPath, filePath);
        File loadFile = storage.loadData(ui);
        if (loadFile != null) {
            tasks = new TaskList(loadFile);
        } else {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
            if (!isExit) {
                ui.displayBlankLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException{
        Duke duke = new Duke("./data", "duke.txt");
        duke.run();
    }

}
