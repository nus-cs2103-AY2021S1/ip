import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Command command;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        parser = new Parser();
        command = new Command();
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        command.receive(ui.readCommand());
        while(!command.exit()) {
            try {
                command.executeTask(parser, tasks, storage, ui);
            } catch (InvalidTaskArgumentException |  InvalidDoneException | InvalidCommandException |
                    InvalidDeleteException | DateException e) {
                ui.showError(e.getMessage());
            } finally {
                command.receive(ui.readCommand());
            }
        }
        ui.showFarewell();
    }

    public static void main(String[] args) {
        try {
            new Duke("data/tasks.txt").run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
