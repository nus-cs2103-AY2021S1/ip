package viscount;

import viscount.command.Command;
import viscount.exception.ViscountException;
import viscount.exception.ViscountIOException;

public class Viscount {
    private static final String DATA_FILE_PATH = System.getProperty("user.dir") + "/data/viscount.txt";
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean shouldRun;
    
    public Viscount(String filePathString) {
        this.storage = new Storage(filePathString);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadFromDisk());
            this.shouldRun = true;
        } catch (ViscountIOException e) {
            ui.showError(e.getMessage());
            this.shouldRun = false;
        }
    }
    
    private void run() {
        ui.showWelcome();
        
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawInput = ui.readInput();
                Command command = Parser.parse(rawInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ViscountException e) {
                ui.showError(e.getMessage());
            }
        }
        
        exit();
    }
    
    private void exit() {
        ui.showExit();
    }

    public static void main(String[] args) {
        Viscount viscount = new Viscount(DATA_FILE_PATH);
        if (viscount.shouldRun) {
            viscount.run();
        } else {
            viscount.exit();
        }
    }
}
