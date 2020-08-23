
import java.io.IOException;

import java.time.format.DateTimeFormatter;

public class Viscount {
    private static final String DATA_FILE_PATH = System.getProperty("user.dir") + "/data/viscount.txt";
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Viscount(String filePathString) {
        this.storage = new Storage(filePathString);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadFromDisk());
        } catch (IOException e) {
            
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
        
        ui.showExit();
    }

    public static void main(String[] args) {
        new Viscount(DATA_FILE_PATH).run();
    }
}
