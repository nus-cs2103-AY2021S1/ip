package dude;

import java.io.FileNotFoundException;

import dude.util.*;

import dude.command.Command;

public class Dude {
    private static final String BYE = "bye";
    private static final String FILEPATH = "./data/tasks.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Dude() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            ui.showFileNotFound();
            tasks = new TaskList();
        } catch (CorruptedFileException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui ,storage);
                isExit = command.isExit();
                ui.showMessage();
            } catch (CommandException | InvalidArgumentException |InvalidCommandException e){
                ui.showError(e.getMessage());
            }
        }
    }
}
